//
//  MangaKuApp.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared

@main
struct MangaKuApp: App {

  private let assembler = AppAssembler()

  init() {
    KoinApplication.start()
    UpdatePropertiesKt.updateProperties(stage: EnvStage.prod)
  }

  var body: some Scene {
    WindowGroup {
      TabNavigationView(assembler: assembler)
    }
  }
}
