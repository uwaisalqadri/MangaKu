//
//  Koin.swift
//  iosApp
//
//  Created by Uwais Alqadri on 7/10/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import KotlinCore
import Foundation

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

extension KoinApplication {
  static let shared = companion.start()

  @discardableResult
  static func start() -> KoinApplication {
    shared
  }
}

extension KoinApplication {
  private static let keyPaths: [PartialKeyPath<Koin>] = [
    \.browseUseCase,
    \.searchUseCase,
    \.myMangaUseCase,
    \.detailUseCase
  ]

  static func inject<T>() -> T {
    shared.inject()
  }

  func inject<T>() -> T {
    for partialKeyPath in Self.keyPaths {
      guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
      return koin[keyPath: keyPath]
    }

    fatalError("\(T.self) is not registered with KoinApplication")
  }
}

@propertyWrapper
struct LazyKoin<T> {
  lazy var wrappedValue: T = { KoinApplication.shared.inject() }()

  init() { }
}
