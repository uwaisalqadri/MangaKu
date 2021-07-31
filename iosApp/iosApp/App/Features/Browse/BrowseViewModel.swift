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

  @Published var loading = false
  @Published var mangas = [Manga]()
  @Published var trendingManga = [Manga]()

  private let listUseCase: GetMangaListUseCase
  private let trendingUseCase: GetMangaTrendingUseCase
  private var cancellables = Set<AnyCancellable>()

  init(listUseCase: GetMangaListUseCase, trendingUseCase: GetMangaTrendingUseCase) {
    self.listUseCase = listUseCase
    self.trendingUseCase = trendingUseCase
  }

  func fetchManga() {
    // create publisher for kotlin flow
    createPublisher(for: listUseCase.executeNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
      } receiveValue: { value in
        self.mangas = value
      }.store(in: &cancellables)
  }

  func fetchTrendingManga() {
    createPublisher(for: trendingUseCase.executeNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
      } receiveValue: { value in
        self.trendingManga = value
      }.store(in: &cancellables)
  }
}
