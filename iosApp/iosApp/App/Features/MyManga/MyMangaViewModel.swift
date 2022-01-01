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

  @Published var mangas = [Manga]()
  @Published var errorMessage = ""
  @Published var isLoading = false
  @Published var isFavorite = false

  private let myMangaUseCase: MyMangaUseCase
  private var cancellables = Set<AnyCancellable>()
  let backgroundQueue = DispatchQueue(label: "com.schedulers.dispatch.mangaku", qos: .background)

  init(myMangaUseCase: MyMangaUseCase) {
    self.myMangaUseCase = myMangaUseCase
    fetchFavoriteManga()
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
      .subscribe(on: backgroundQueue)
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.isLoading = false
        case .failure(let error):
          guard let apiError = error.apiError else { return }
          self.errorMessage = apiError.errorMessage
        }
      } receiveValue: { value in
        value.forEach { item in
          self.isFavorite = item.id == mangaId
        }
      }.store(in: &cancellables)
  }

  func fetchFavoriteManga() {
    isLoading = true
    createPublisher(for: myMangaUseCase.getMyMangaNative())
      .subscribe(on: backgroundQueue)
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.isLoading = false
        case .failure(let error):
          guard let apiError = error.apiError else { return }
          self.errorMessage = apiError.errorMessage
        }
      } receiveValue: { value in
        self.mangas = value
      }.store(in: &cancellables)
  }
}
