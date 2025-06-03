//
//  DetailViewModel+ActionState.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 30/05/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import Shared

extension DetailViewModel {
  struct State {
    var mangaId: String
    var manga: Manga? = nil
    var isLoading: Bool = false
    var errorMessage: String = ""
  }
  
  enum Action {
    case getDetail(mangaId: String)
  }
}
