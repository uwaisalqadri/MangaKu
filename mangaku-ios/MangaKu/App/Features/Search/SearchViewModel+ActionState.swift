//
//  SearchViewModel+ActionState.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 30/05/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import Shared

extension SearchViewModel {
  struct State {
    var searchQuery: String = ""
    var mangas: [Manga] = []
    var isLoading: Bool = false
    var isEmpty: Bool = false
    var errorMessage: String = ""
  }
  
  enum Action {
    case empty
    case getManga(query: String)
  }
}
