//
//  BrowseViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
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
  private var cancellables = Set<AnyCancellable>()

  init(listUseCase: GetMangaListUseCase, trendingUseCase: GetMangaTrendingUseCase) {
    self.listUseCase = listUseCase
    self.trendingUseCase = trendingUseCase
    fetchManga()
    fetchTrendingManga()
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
