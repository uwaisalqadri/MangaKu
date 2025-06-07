//
//  Flow+Async.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 07/06/25.
//  Copyright © 2025 Uwais Alqadri. All rights reserved.
//

import Foundation
import Shared

extension SkieSwiftStateFlow {
  @MainActor
  func collect(_ handler: @escaping (Element) -> Void) async {
    for await value in self {
      handler(value)
    }
  }
}

extension SkieSwiftFlow {
  @MainActor
  func collect(_ handler: @escaping (Element) -> Void) async {
    for await value in self {
      handler(value)
    }
  }
}

extension SkieSwiftOptionalFlow {
  @MainActor
  func collect(_ handler: @escaping (Element) -> Void) async {
    for await value in self {
      handler(value)
    }
  }
}
