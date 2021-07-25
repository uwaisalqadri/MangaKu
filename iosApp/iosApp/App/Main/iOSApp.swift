//
//  iOSApp.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore

@main
struct iOSApp: App {
  var body: some Scene {
    WindowGroup {
      ContentView()
        .onAppear {
          CoreKt.doInitKoin()
        }
    }
  }
}
