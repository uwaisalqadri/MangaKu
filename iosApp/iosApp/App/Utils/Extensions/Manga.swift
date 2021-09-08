//
//  Manga.swift
//  iosApp
//
//  Created by Uwais Alqadri on 08/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore

extension Manga {
  func getTitle() -> String {
    let title = attributes?.titles
    guard let string = title?.en_us.ifEmpty(title?.ja_jp.ifEmpty(title?.en_jp.ifEmpty(title?.en ?? "") ?? "") ?? "")
    else { return "" }
    return string
  }
}
