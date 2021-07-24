//
//  BrowseAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore

protocol BrowseAssembler {
  func resolve() -> BrowseViewModel
  func resolve() -> GetMangaListUseCase
}

extension BrowseAssembler where Self: Assembler {

  func resolve() -> BrowseViewModel {
    return BrowseViewModel(listUseCase: resolve())
  }

  func resolve() -> GetMangaListUseCase {
    return GetMangaListInteractor()
  }
}
