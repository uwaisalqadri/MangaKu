//
//  BrowseAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import KotlinCore

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
    return BrowseViewModel(browseUseCase: resolve())
  }

  @MainActor func resolve() -> MyMangaViewModel {
    return MyMangaViewModel(myMangaUseCase: resolve())
  }

  @MainActor func resolve() -> SearchViewModel {
    return SearchViewModel(searcUseCase: resolve())
  }

  @MainActor func resolve() -> DetailViewModel {
    return DetailViewModel(detailUseCase: resolve())
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
