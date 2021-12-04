//
//  SearchItemView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI

struct SearchItemView: View {

  let manga: Manga
  private let extensions = Extensions()

  var body: some View {
    WebImage(url: URL(string: extensions.getPosterImage(manga: manga)))
      .resizable()
      .indicator(.activity)
      .frame(height: 140)
      .cornerRadius(8)
      .padding(.bottom, 20)
  }
}
