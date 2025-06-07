//
//  MyMangaViewModel.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 30/05/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

@MainActor
final class MyMangaViewModel: ObservableObject {
  @Published
  var state: State = State()
  
  @Inject
  private var getUseCase: GetMyMangaUseCase
  @Inject
  private var getByIdUseCase: GetMyMangaByIdUseCase
  @Inject
  private var addUseCase: AddMangaUseCase
  @Inject
  private var deleteUseCase: DeleteMangaUseCase
  
  func send(action: Action) {
    switch action {
    case .getMyMangas:
      Task { await getMyMangas() }
      
    case let .checkFavorite(mangaId):
      Task { await checkFavorite(mangaId) }
      
    case let .addFavorite(manga):
      Task { await addMyManga(manga) }
      
    case let .deleteFavorite(mangaId):
      Task { await deleteMyManga(mangaId) }
    }
  }

}

extension MyMangaViewModel {
  @MainActor
  func addMyManga(_ manga: Manga) async {
    for try await result in addUseCase.execute(request: manga) {
      if let result = result as? KotlinUnit {
        await checkFavorite(manga.id)
      }
    }
  }
  
  @MainActor
  func deleteMyManga(_ mangaId: String) async {
    for try await result in deleteUseCase.execute(request: mangaId) {
      if let result = result as? KotlinUnit {
          await checkFavorite(mangaId)
      }
    }
  }
  
  @MainActor
  func checkFavorite(_ mangaId: String) async {
    for try await result in getByIdUseCase.execute(request: mangaId) {
      if let result = result as? [Manga] {
        let isFavorite = result.contains { $0.id == mangaId }
        state.isFavorite = isFavorite
      }
    }
  }
  
  @MainActor
  func getMyMangas() async {
    state.isLoading = true
    defer { state.isLoading = false }
    
    for try await result in getUseCase.execute() {
      if let result = result as? [Manga], !result.isEmpty {
        state.items = result
      }
    }
  }
}
