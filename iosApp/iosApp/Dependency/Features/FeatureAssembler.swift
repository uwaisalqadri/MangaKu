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
  func resolve() -> MyMangaViewModel
  func resolve() -> SearchViewModel
  func resolve() -> DetailViewModel
}

extension FeatureAssembler where Self: Assembler {

  @MainActor func resolve() -> BrowseViewModel {
    return BrowseViewModel(browseUseCase: resolve())
  }

  func resolve() -> MyMangaViewModel {
    return MyMangaViewModel(myMangaUseCase: resolve())
  }

  func resolve() -> SearchViewModel {
    return SearchViewModel(searcUseCase: resolve())
  }

  func resolve() -> DetailViewModel {
    return DetailViewModel(detailUseCase: resolve())
  }
}
