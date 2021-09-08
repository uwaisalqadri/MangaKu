//
//  String.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

extension String {

  func toDate(withFormat format: String = "yyyy-MM-dd'T'HH:mm:ss.fff'Z'")-> Date? {
    let dateFormatter = DateFormatter()
    dateFormatter.calendar = Calendar(identifier: .gregorian)
    dateFormatter.dateFormat = format
    let date = dateFormatter.date(from: self)
    return date
  }

  func ifEmpty(_ fallback: String) -> String {
    if self.isEmpty{
      return fallback
    }
    return self
  }

}
