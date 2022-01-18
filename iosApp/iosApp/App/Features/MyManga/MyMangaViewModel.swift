//
//  MyMangaViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import Combine
import KotlinCore
import KMPNativeCoroutinesAsync

@MainActor
class MyMangaViewModel: ObservableObject {

  @Published var listManga: ViewState<[Manga]> = .initiate
  @Published var isFavorite = false

  private let myMangaUseCase: MyMangaUseCase
  private var cancellables = Set<AnyCancellable>()

  init(myMangaUseCase: MyMangaUseCase) {
    self.myMangaUseCase = myMangaUseCase
  }

  func addFavoriteManga(manga: Manga) {
    myMangaUseCase.addManga(manga: manga)
    isFavorite = true
  }

  func removeFavoriteManga(mangaId: String) {
    myMangaUseCase.deleteManga(mangaId: mangaId)
    isFavorite = false
  }

  func checkFavorite(mangaId: String) {
    Task {
      do {
        let nativeFlow = try await asyncFunction(for: myMangaUseCase.getMyMangaByIdNative(mangaId: mangaId))
        let stream = asyncStream(for: nativeFlow)
        for try await value in stream {
          value.forEach { item in
            self.isFavorite = item.id == mangaId
          }
        }
      } catch {
        print("")
      }
    }
  }

  func fetchFavoriteManga() {
    Task {
      listManga = .loading
      do {
        let nativeFlow = try await asyncFunction(for: myMangaUseCase.getMyMangaNative())
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
