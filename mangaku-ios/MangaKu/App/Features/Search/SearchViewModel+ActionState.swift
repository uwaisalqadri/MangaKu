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
    var items: [Manga] = []
    var isLoading: Bool = false
    var errorMessage: String = ""
  }
  
  enum Action {
    case getManga(query: String)
  }
}
