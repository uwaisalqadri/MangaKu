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
import KMPNativeCoroutinesAsync

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
  func addMyManga(_ manga: Manga) async {
    try? await asyncSequence(for: addUseCase.execute(request: manga.id))
    await checkFavorite(manga.id)
  }
  
  func deleteMyManga(_ mangaId: String) async {
    try? await asyncSequence(for: deleteUseCase.execute(request: mangaId))
    await checkFavorite(mangaId)
  }
  
  func checkFavorite(_ mangaId: String) async {
    do {
      for try await result in asyncSequence(for: getByIdUseCase.execute(request: mangaId)) {
        if let result = result as? [Manga] {
          let isFavorite = result.contains { $0.id == mangaId }
          print("checkFavorite(mangaId: \(mangaId)): \(isFavorite) || \(result)")
          state.isFavorite = isFavorite
        }
      }
    } catch {
      print("Failed to check favorite: \(error)")
    }
  }
  
  func getMyMangas() async {
    state.isLoading = true
    defer { state.isLoading = false }
    
    do {
      for try await result in asyncSequence(for: getUseCase.execute()) {
        if let result = result as? [Manga], !result.isEmpty {
          state.items = result
        }
      }
    } catch {
      state.errorMessage = error.localizedDescription
    }
  }
}
