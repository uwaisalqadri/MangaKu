//
//  MyMangaViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import KotlinCore
import KMPNativeCoroutinesCombine

class MyMangaViewModel: ObservableObject {

  @Published var mangas = [Manga]()
  @Published var errorMessage = ""
  @Published var loading = false
  @Published var isFavorite = false
//  @Published var favState: FavState

  private let favoriteUseCase: GetMangaFavoriteUseCase
  private var cancellables = Set<AnyCancellable>()
  let backgroundQueue = DispatchQueue(label: "com.schedulers.dispatch.mangaku", qos: .background)

  init(favoriteUseCase: GetMangaFavoriteUseCase) {
    self.favoriteUseCase = favoriteUseCase
    fetchFavoriteManga()
  }

  func addFavoriteManga(manga: Manga) {
    favoriteUseCase.add(manga: manga)
    isFavorite = true
  }

  func removeFavoriteManga(mangaId: String) {
    favoriteUseCase.delete(mangaId: mangaId)
    isFavorite = false
  }

  func checkFavorite(mangaId: String) {
    createPublisher(for: favoriteUseCase.getByIdNative(mangaId: mangaId))
      .subscribe(on: backgroundQueue)
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        value.forEach { item in
          self.isFavorite = item.id == mangaId
        }
      }.store(in: &cancellables)
  }

  func fetchFavoriteManga() {
    loading = true
    createPublisher(for: favoriteUseCase.getNative())
      .subscribe(on: backgroundQueue)
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        self.mangas = value
      }.store(in: &cancellables)
  }
}
