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
  func resolve() -> BrowseRouter
  func resolve() -> MyMangaRouter
  func resolve() -> SearchRouter
  func resolve() -> DetailRouter
}

extension FeatureAssembler where Self: Assembler {

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
