//
//  DetailViewModel.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 30/05/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

@MainActor
final class DetailViewModel: ObservableObject {
  @Published
  var state: State
  
  @Inject
  private var detailUseCase: DetailUseCase
  
  init(mangaId: String) {
    state = State(mangaId: mangaId)
  }
  
  func send(action: Action) {
    switch action {
    case let .getDetail(mangaId):
      Task { await getDetail(mangaId: mangaId) }
    }
  }
}

extension DetailViewModel {
  @MainActor
  func getDetail(mangaId: String) async {
    state.isLoading = true
    defer { state.isLoading = false }
    
    for try await data in detailUseCase.execute(request: mangaId) {
      state.manga = data
    }
  }
}
