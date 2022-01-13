//
//  MyMangaViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import Combine
import KotlinCore
import KMPNativeCoroutinesCombine

class MyMangaViewModel: ObservableObject {

  @Published var listManga: ViewState<[Manga]> = .initiate
  @Published var isFavorite = false

  private let myMangaUseCase: MyMangaUseCase
  private var cancellables = Set<AnyCancellable>()

  init(myMangaUseCase: MyMangaUseCase) {
    self.myMangaUseCase = myMangaUseCase
  }

  func addFavoriteManga(manga: Manga) {
    myMangaUseCase.addManga(manga: manga)
    isFavorite = true
  }

  func removeFavoriteManga(mangaId: String) {
    myMangaUseCase.deleteManga(mangaId: mangaId)
    isFavorite = false
  }

  func checkFavorite(mangaId: String) {
    createPublisher(for: myMangaUseCase.getMyMangaByIdNative(mangaId: mangaId))
      .subscribe(on: DispatchQueue.global(qos: .background))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished: ()
        case .failure: ()
        }
      } receiveValue: { value in
        value.forEach { item in
          self.isFavorite = item.id == mangaId
        }
      }.store(in: &cancellables)
  }

  func fetchFavoriteManga() {
    listManga = .loading
    createPublisher(for: myMangaUseCase.getMyMangaNative())
      .subscribe(on: DispatchQueue.global(qos: .background))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished: ()
        case .failure(let error):
          self.listManga = .error(error: error)
        }
      } receiveValue: { value in
        self.listManga = value.isEmpty ? .empty : .success(data: value)
      }.store(in: &cancellables)
  }
}
