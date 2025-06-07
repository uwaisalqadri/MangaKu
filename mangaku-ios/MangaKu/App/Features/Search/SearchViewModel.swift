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
    
    for try await result in searchUseCase.execute(request: query) {
      if let result = result as? [Manga] {
        state.items = result
      }
    }
  }
}
