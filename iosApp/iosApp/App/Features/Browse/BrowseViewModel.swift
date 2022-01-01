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

  @Published var mangas = [Manga]()
  @Published var trendingManga = [Manga]()
  @Published var isLoading = false
  @Published var errorMessage = ""

  private let browseUseCase: BrowseUseCase
  private var cancellables = Set<AnyCancellable>()

  init(browseUseCase: BrowseUseCase) {
    self.browseUseCase = browseUseCase
  }

  func fetchManga() {
    Task {
      do {
        isLoading = true
        let stream = asyncStream(for: browseUseCase.getMangaNative())
        for try await data in stream {
          // mangas = data
          isLoading = false
        }
      } catch {
        errorMessage = error.localizedDescription
        isLoading = false
      }
    }
  }
  
  func fetchTrendingManga() {
    isLoading = true
    createPublisher(for: browseUseCase.getMangaNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.isLoading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        self.trendingManga = value
      }.store(in: &cancellables)
  }
}
