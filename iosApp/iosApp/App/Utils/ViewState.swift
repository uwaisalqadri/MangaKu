//
//  ViewState.swift
//  iosApp
//
//  Created by Uwais Alqadri on 1/3/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import Foundation

enum ViewState<T>: Equatable {
  static func == (lhs: ViewState<T>, rhs: ViewState<T>) -> Bool {
    return true
  }

  case initiate
  case loading
  case empty
  case error(error: Error)
  case success(data: T)

  var value: T? {
    if case .success(let data) = self {
      return data
    }
    return nil
  }
}
