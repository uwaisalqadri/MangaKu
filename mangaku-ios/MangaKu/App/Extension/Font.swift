//
//  Font.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 8/28/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

enum FontType: String {
  case mmedium = "montserrat-medium"
  case msemibold = "montserrat-semibold"
  case mbold = "montserrat-bold"
  case mashanzheng = "mashanzheng-regular"
  case sedgwickave = "sedgwickavedisplay-regular"
}

extension Font {
  static func custom(_ font: FontType, size: CGFloat) -> Font {
    return Font.custom(font.rawValue, size: size)
  }
}
