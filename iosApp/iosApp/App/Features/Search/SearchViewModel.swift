//
//  SavedViewModel.swift
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
class SearchViewModel: ObservableObject {

  @Published var listManga: ViewState<[Manga]> = .initiate

  @LazyKoin private var searcUseCase: SearchUseCase
  private var cancellables = Set<AnyCancellable>()

  func fetchSearchManga(query: String) {
    Task {
      listManga = .loading
      do {
        let nativeFlow = try await asyncFunction(for: searcUseCase.getSearchMangaNative(query: query))
        let stream = asyncStream(for: nativeFlow)
        for try await value in stream {
          listManga = value.isEmpty ? .empty : .success(data: value)
        }
      } catch {
        listManga = .error(error: error)
      }
    }
  }

}
