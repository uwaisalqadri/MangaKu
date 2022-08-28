//
//  BrowseAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import Shared

protocol FeatureAssembler {
  @MainActor func resolve() -> BrowseViewModel
  @MainActor func resolve() -> MyMangaViewModel
  @MainActor func resolve() -> SearchViewModel
  @MainActor func resolve() -> DetailViewModel

  func resolve() -> BrowseRouter
  func resolve() -> MyMangaRouter
  func resolve() -> SearchRouter
  func resolve() -> DetailRouter
}

extension FeatureAssembler where Self: Assembler {

  @MainActor func resolve() -> BrowseViewModel {
    return BrowseViewModel()
  }

  @MainActor func resolve() -> MyMangaViewModel {
    return MyMangaViewModel()
  }

  @MainActor func resolve() -> SearchViewModel {
    return SearchViewModel()
  }

  @MainActor func resolve() -> DetailViewModel {
    return DetailViewModel()
  }

  func resolve() -> BrowseRouter {
    return BrowseRouter(asssembler: self)
  }

  func resolve() -> MyMangaRouter {
    return MyMangaRouter(assembler: self)
  }

  func resolve() -> SearchRouter {
    return SearchRouter(assembler: self)
  }

  func resolve() -> DetailRouter {
    return DetailRouter(assembler: self)
  }
}
