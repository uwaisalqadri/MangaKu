//
//  Weakifiable.swift
//  iosApp
//
//  Created by Uwais Alqadri on 31/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

protocol Weakifiable: class {}

extension NSObject: Weakifiable {}

extension Weakifiable {

  func weakify<T>(_ code: @escaping (Self, T) -> Void) -> (T) -> Void {
    return { [weak self] (data) in
      guard let self = self else { return }
      code(self, data)
    }
  }
}
