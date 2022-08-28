//
//  Error.swift
//  iosApp
//
//  Created by Uwais Alqadri on 1/1/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import Foundation
import Shared

extension Error {
  var apiError: ApiError? {
    (self as NSError).userInfo["KotlinException"] as? ApiError
  }
}

