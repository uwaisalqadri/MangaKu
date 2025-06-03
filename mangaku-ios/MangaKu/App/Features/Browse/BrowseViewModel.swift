//
//  BrowseViewModel.swift
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
final class BrowseViewModel: ObservableObject {
  @Published
  var state: State
  
  @Inject
  private var browseUseCase: BrowseUseCase
  
  init() {
    state = State()
  }
  
  func send(action: Action) {
    switch action {
    case .getMangas:
      Task { await getAllManga() }
    }
  }
}

extension BrowseViewModel {
  @MainActor
  func getAllManga() async {
    state.isLoading = true
    defer { state.isLoading = false }
    
    do {
      for try await data in asyncSequence(for: browseUseCase.execute()) {
        if let items = data as? [Manga], !items.isEmpty {
          state.items = items
        }
      }
    } catch {
      state.errorMessage = error.localizedDescription
    }
  }
}
