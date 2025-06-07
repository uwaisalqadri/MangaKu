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
    
    for await data in browseUseCase.execute() {
      if let data = data as? [Manga] {
        state.items = data
      }
    }
  }
}
