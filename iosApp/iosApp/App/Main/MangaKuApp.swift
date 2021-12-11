//
//  MangaKuApp.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import KotlinCore

@main
struct MangaKuApp: App {
  var body: some Scene {
    WindowGroup {
      TabNavigationView()
        .onAppear {
          CoreModuleKt.doInitKoin()
        }
    }
  }
}
