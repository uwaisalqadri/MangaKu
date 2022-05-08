//
//  SearchRow.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI

struct SearchRow: View {

  let manga: Manga

  var body: some View {
    WebImage(url: URL(string: manga.getPosterImage()))
      .resizable()
      .indicator(.activity)
      .frame(height: 140)
      .cornerRadius(8)
      .padding(.bottom, 20)
  }
}
