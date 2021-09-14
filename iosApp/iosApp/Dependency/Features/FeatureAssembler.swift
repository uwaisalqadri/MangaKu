//
//  BrowseAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore

protocol FeatureAssembler {
  func resolve() -> BrowseViewModel
  func resolve() -> MyMangaViewModel
  func resolve() -> SearchViewModel
  func resolve() -> DetailViewModel
}

extension FeatureAssembler where Self: Assembler {

  func resolve() -> BrowseViewModel {
    return BrowseViewModel(listUseCase: resolve(), trendingUseCase: resolve(), favoriteUseCase: resolve())
  }

  func resolve() -> MyMangaViewModel {
    return MyMangaViewModel(favoriteUseCase: resolve())
  }

  func resolve() -> SearchViewModel {
    return SearchViewModel(searcUseCase: resolve())
  }

  func resolve() -> DetailViewModel {
    return DetailViewModel(detailUseCase: resolve())
  }
}
