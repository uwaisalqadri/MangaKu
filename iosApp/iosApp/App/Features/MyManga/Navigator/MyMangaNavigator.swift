//
//  MyMangaNavigator.swift
//  iosApp
//
//  Created by Uwais Alqadri on 1/1/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct MyMangaNavigator {
  private let assembler: Assembler

  init(assembler: Assembler) {
    self.assembler = assembler
  }

  @MainActor func navigateToMyManga() -> some View {
    MyMangaView(viewModel: assembler.resolve(), navigator: self)
  }

  @MainActor func navigateToDetailView(mangaId: String) -> some View {
    let navigator: DetailNavigator = assembler.resolve()
    return navigator.navigateToDetailView(mangaId: mangaId)
  }
}
