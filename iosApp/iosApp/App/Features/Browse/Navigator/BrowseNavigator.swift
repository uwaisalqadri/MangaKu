//
//  BrowseNavigator.swift
//  iosApp
//
//  Created by Uwais Alqadri on 1/1/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct BrowseNavigator {
  private let asssembler: Assembler

  init(asssembler: Assembler) {
    self.asssembler = asssembler
  }

  @MainActor func navigateToBrowseView() -> some View {
    BrowseView(viewModel: asssembler.resolve(), navigator: self)
  }

  func navigateToDetailView(mangaId: String) -> some View {
    let navigator: DetailNavigator = asssembler.resolve()
    return navigator.navigateToDetailView(mangaId: mangaId)
  }

  func navigateToSearchView() -> some View {
    let navigator: SearchNavigator = asssembler.resolve()
    return navigator.navigateToSearchView()
  }
}


