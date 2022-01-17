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
  func resolve() -> BrowseNavigator

  @MainActor func resolve() -> MyMangaViewModel
  func resolve() -> MyMangaNavigator

  @MainActor func resolve() -> SearchViewModel
  func resolve() -> SearchNavigator

  @MainActor func resolve() -> DetailViewModel
  func resolve() -> DetailNavigator
}

extension FeatureAssembler where Self: Assembler {

  @MainActor func resolve() -> BrowseViewModel {
    return BrowseViewModel(browseUseCase: resolve())
  }

  func resolve() -> BrowseNavigator {
    return BrowseNavigator(asssembler: self)
  }

  @MainActor func resolve() -> MyMangaViewModel {
    return MyMangaViewModel(myMangaUseCase: resolve())
  }

  func resolve() -> MyMangaNavigator {
    return MyMangaNavigator(assembler: self)
  }

  @MainActor func resolve() -> SearchViewModel {
    return SearchViewModel(searcUseCase: resolve())
  }

  func resolve() -> SearchNavigator {
    return SearchNavigator(assembler: self)
  }

  @MainActor func resolve() -> DetailViewModel {
    return DetailViewModel(detailUseCase: resolve())
  }

  func resolve() -> DetailNavigator {
    return DetailNavigator(assembler: self)
  }
}
