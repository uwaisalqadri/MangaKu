//
//  DetailViewModel+ActionState.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 30/05/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import Shared

extension MyMangaViewModel {
  struct State {
    var items: [Manga] = []
    var isFavorite: Bool = false
    var isLoading: Bool = false
    var errorMessage: String = ""
  }
  
  enum Action {
    case getMyMangas
    case checkFavorite(mangaId: String)
    case addFavorite(manga: Manga)
    case deleteFavorite(mangaId: String)
  }
}
