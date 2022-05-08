//
//  ViewStateModifier.swift
//  iosApp
//
//  Created by Uwais Alqadri on 5/9/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct ViewStateModifier<Data>: ViewModifier {

  var data: Published<ViewState<Data>>.Publisher
  var onSuccess: ((Data) -> Void)?
  var onLoading: (() -> Void)?
  var onEmpty: (() -> Void)?
  var onError: ((Error) -> Void)?

  func body(content: Content) -> some View {
    content
      .onReceive(data) { state in
        switch state {
        case .success(let data):
          onSuccess?(data)
        case .loading:
          onLoading?()
        case .empty:
          onEmpty?()
        case .error(let error):
          onError?(error)
        default:
          break
        }
      }
  }
}

extension View {
  func onViewStatable<Data>(
    _ data: Published<ViewState<Data>>.Publisher,
    onSuccess: ((Data) -> Void)? = nil,
    onLoading: (() -> Void)? = nil,
    onEmpty: (() -> Void)? = nil,
    onError: ((Error) -> Void)? = nil
  ) -> some View {
    modifier(ViewStateModifier(data: data, onSuccess: onSuccess, onLoading: onLoading, onEmpty: onEmpty, onError: onError))
  }
}

