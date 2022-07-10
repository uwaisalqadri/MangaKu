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
import KMPNativeCoroutinesAsync

@MainActor
class BrowseViewModel: ObservableObject {

  @Published var trendingManga: ViewState<[Manga]> = .initiate
  @LazyKoin private var browseUseCase: BrowseUseCase

  private var cancellables = Set<AnyCancellable>()

  init() {
    fetchTrendingManga()
  }

  func fetchTrendingManga() {
    Task {
      trendingManga = .loading
      do {
        let nativeFlow = try await asyncFunction(for: browseUseCase.getTrendingMangaNative())
        let stream = asyncStream(for: nativeFlow)
        for try await data in stream {
          trendingManga = .success(data: data)
        }
      } catch {
        trendingManga = .error(error: error)
      }
    }
  }
}
