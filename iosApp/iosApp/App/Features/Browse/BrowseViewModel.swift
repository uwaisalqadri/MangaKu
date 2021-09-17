//
//  BrowseViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore
import Combine
import KMPNativeCoroutinesCombine

class BrowseViewModel: ObservableObject {

  @Published var mangas = [Manga]()
  @Published var trendingManga = [Manga]()
  @Published var loading = false
  @Published var errorMessage = ""

  private let listUseCase: GetMangaListUseCase
  private let trendingUseCase: GetMangaTrendingUseCase
  private let createFavoriteUseCase: CreateMangaFavoriteUseCase
  private var cancellables = Set<AnyCancellable>()

  init(listUseCase: GetMangaListUseCase, trendingUseCase: GetMangaTrendingUseCase, createFavoriteUseCase: CreateMangaFavoriteUseCase) {
    self.listUseCase = listUseCase
    self.trendingUseCase = trendingUseCase
    self.createFavoriteUseCase = createFavoriteUseCase
    fetchManga()
    fetchTrendingManga()
  }

  func addFavoriteManga(manga: Manga) {
    var ids = [String]()
    trendingManga.forEach { item in
      ids.append(item.id)
    }

    if ids.contains(manga.id) {
      createFavoriteUseCase.add(manga: manga)
      print("added")
    } else {
      print("removed")
    }
  }

  func removeFavoriteManga(mangaId: String) {
    createFavoriteUseCase.delete(mangaId: mangaId)
  }

  private func fetchManga() {
    loading = true
    createPublisher(for: listUseCase.invokeNative())
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
  
  private func fetchTrendingManga() {
    loading = true
    createPublisher(for: trendingUseCase.invokeNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        self.trendingManga = value
      }.store(in: &cancellables)
  }
}
