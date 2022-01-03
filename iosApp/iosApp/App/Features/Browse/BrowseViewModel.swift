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

class BrowseViewModel: ObservableObject {

  @Published var trendingManga: ViewState<[Manga]> = .initiate

  private let browseUseCase: BrowseUseCase
  private var cancellables = Set<AnyCancellable>()

  init(browseUseCase: BrowseUseCase) {
    self.browseUseCase = browseUseCase

    fetchTrendingManga()
  }

//  func fetchManga() {
//    Task {
//      do {
//        isLoading = true
//        let stream = asyncStream(for: browseUseCase.getMangaNative())
//        for try await data in stream {
//          // mangas = data
//          isLoading = false
//        }
//      } catch {
//        errorMessage = error.localizedDescription
//        isLoading = false
//      }
//    }
//  }
  
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
