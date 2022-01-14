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

  func resolve() -> MyMangaViewModel
  func resolve() -> MyMangaNavigator

  func resolve() -> SearchViewModel
  func resolve() -> SearchNavigator

  func resolve() -> DetailViewModel
  func resolve() -> DetailNavigator
}

extension FeatureAssembler where Self: Assembler {

  @MainActor func resolve() -> BrowseViewModel {
    return BrowseViewModel(browseUseCase: resolve())
  }

  func resolve() -> BrowseNavigator {
    return BrowseNavigator(asssembler: self)
  }

  func resolve() -> MyMangaViewModel {
    return MyMangaViewModel(myMangaUseCase: resolve())
  }

  func resolve() -> MyMangaNavigator {
    return MyMangaNavigator(assembler: self)
  }

  func resolve() -> SearchViewModel {
    return SearchViewModel(searcUseCase: resolve())
  }

  func resolve() -> SearchNavigator {
    return SearchNavigator(assembler: self)
  }

  func resolve() -> DetailViewModel {
    return DetailViewModel(detailUseCase: resolve())
  }

  func resolve() -> DetailNavigator {
    return DetailNavigator(assembler: self)
  }
}
