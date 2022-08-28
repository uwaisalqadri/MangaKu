//
//  UIScreen.swift
//  MangaKu
//
//  Created by Uwais Alqadri on 8/28/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

extension UIScreen {
  static var safeAreaInsets: UIEdgeInsets? {
    return UIApplication.shared.windows.first?.safeAreaInsets
  }

  static var screenWidth: CGFloat {
    get {
      if UIDevice.current.orientation.isLandscape {
        return max(UIScreen.main.bounds.size.height, UIScreen.main.bounds.size.width)
      } else {
        return min(UIScreen.main.bounds.size.height, UIScreen.main.bounds.size.width)
      }
    }
  }

  static var screenHeight: CGFloat {
    get {
      if UIDevice.current.orientation.isLandscape {
        return max(UIScreen.main.bounds.size.width, UIScreen.main.bounds.size.height)
      } else {
        return max(UIScreen.main.bounds.size.width, UIScreen.main.bounds.size.height)
      }
    }
  }
}
