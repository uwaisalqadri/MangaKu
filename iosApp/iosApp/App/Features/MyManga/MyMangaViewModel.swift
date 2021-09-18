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
  private var ids = [String]()

  private let favoriteUseCase: GetMangaFavoriteUseCase
  private let createFavoriteUseCase: CreateMangaFavoriteUseCase
  private var cancellables = Set<AnyCancellable>()
  let backgroundQueue = DispatchQueue(label: "com.schedulers.dispatch.mangaku", qos: .background)

  init(favoriteUseCase: GetMangaFavoriteUseCase, createFavoriteUseCase: CreateMangaFavoriteUseCase) {
    self.favoriteUseCase = favoriteUseCase
    self.createFavoriteUseCase = createFavoriteUseCase
    fetchFavoriteManga()
  }

  func addFavoriteManga(manga: Manga, isSuccess: () -> Void) {
    mangas.forEach { item in
      ids.append(item.id)
    }

    if !ids.contains(manga.id) {
      createFavoriteUseCase.add(manga: manga)
      isSuccess()
      isFavorite = true
    }
  }

  func removeFavoriteManga(mangaId: String) {
    createFavoriteUseCase.delete(mangaId: mangaId)
  }

  func checkFavorite(mangaId: String) {
    mangas.forEach { manga in
      ids.append(manga.id)
      let listId = ids.joined(separator: ",")
      if listId.contains(mangaId) {
        isFavorite = true
      } else {
        isFavorite = false
      }
    }
  }

  private func fetchFavoriteManga() {
    loading = true
    createPublisher(for: favoriteUseCase.invokeNative())
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
