//
//  KMMViewModel.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 1/20/23.
//  Copyright © 2023 Uwais Alqadri. All rights reserved.
//

import Shared
import KMMViewModelCore

extension Kmm_viewmodel_coreKMMViewModel: KMMViewModel { }

enum ResultEnum<T: AnyObject> {
  case loading
  case none
  case empty
  case success(Shared.ResultSuccess<T>)
  case failure(Shared.ResultFailure<T>)

  public init(_ obj: Result<T>) {
    if obj is Shared.ResultLoading {
      self = .loading
    } else if obj is Shared.ResultEmpty {
      self = .none
    } else if obj is Shared.ResultDefault {
      self = .empty
    } else if let obj = obj as? Shared.ResultSuccess<T> {
      self = .success(obj)
    } else if let obj = obj as? Shared.ResultFailure {
      self = .failure(obj)
    } else {
      fatalError("ResultEnum not syncronized with Result class")
    }
  }
}

//extension Shared.Result where T == AnyObject {
//  static func asEnum() -> ResultEnum<AnyObject> {
//    return ResultEnum(T)
//  }
//}
