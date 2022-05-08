//
//  DetailRouter.swift
//  iosApp
//
//  Created by Uwais Alqadri on 1/1/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct DetailRouter {
  private let assembler: Assembler

  init(assembler: Assembler) {
    self.assembler = assembler
  }

  @MainActor func routeToDetail(mangaId: String) -> some View {
    DetailView(viewModel: assembler.resolve(), mangaViewModel: assembler.resolve(), mangaId: mangaId)
  }
}
