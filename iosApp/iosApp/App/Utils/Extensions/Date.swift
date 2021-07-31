//
//  Date.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

extension Date {

  func toString() -> String? {
    let dateFormatter = DateFormatter()
    dateFormatter.dateFormat = "dd-MM-YYYY"
    let stringDate = dateFormatter.string(from: self)
    return stringDate
  }
}
