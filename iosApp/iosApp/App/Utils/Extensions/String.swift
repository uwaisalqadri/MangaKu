//
//  Double.swift
//  iosApp
//
//  Created by Uwais Alqadri on 17/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation

extension String {

  func removeCharacters(from forbiddenChars: CharacterSet) -> String {
    let passed = self.unicodeScalars.filter { !forbiddenChars.contains($0) }
    return String(String.UnicodeScalarView(passed))
  }

  func removeCharacters(from: String) -> String {
    return removeCharacters(from: CharacterSet(charactersIn: from))
  }

}
