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
import KMPNativeCoroutinesAsync

@MainActor
class BrowseViewModel: ObservableObject {

  @Published var trendingManga: ViewState<[Manga]> = .initiate

  private let browseUseCase: BrowseUseCase
  private var cancellables = Set<AnyCancellable>()

  init(browseUseCase: BrowseUseCase) {
    self.browseUseCase = browseUseCase

    fetchManga()
  }

  func fetchManga() {
    Task {
      trendingManga = .loading
      let manga = await asyncResult(for: browseUseCase.getMangaNative())
      switch manga {
      case .success(let data):
        trendingManga = .success(data: data)
      case .failure(let error):
        trendingManga = .error(error: error)
      }
    }
  }
  
  func fetchTrendingManga() {
    trendingManga = .loading
    createPublisher(for: browseUseCase.getTrendingMangaNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished: ()
        case .failure(let error):
          self.trendingManga = .error(error: error)
        }
      } receiveValue: { value in
        self.trendingManga = .success(data: value)
      }.store(in: &cancellables)
  }
}
