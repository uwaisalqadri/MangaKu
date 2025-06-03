//
//  SearchViewModel.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 30/05/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import SwiftUI
import Shared
import KMPNativeCoroutinesAsync

@MainActor
final class SearchViewModel: ObservableObject {
  @Published
  var state: State
  
  @Inject
  private var searchUseCase: SearchUseCase
  
  init() {
    state = State()
  }
  
  func send(action: Action) {
    switch action {
    case .empty:
      state.mangas = []
    case let .getManga(query):
      Task { await getSearchManga(query: query) }
    }
  }
}

extension SearchViewModel {
  @MainActor
  func getSearchManga(query: String) async {
    state.isLoading = true
    defer { state.isLoading = false }
    
    do {
      for try await result in asyncSequence(for: searchUseCase.execute(request: query)) {
        if let result = result as? [Manga] {
          state.mangas = result
          state.isEmpty = result.isEmpty
        }
      }
    } catch {
      state.errorMessage = error.localizedDescription
    }
  }
}
