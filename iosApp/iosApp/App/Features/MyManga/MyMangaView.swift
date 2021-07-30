//
//  MyMangaView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI

struct MyMangaView: View {

  @ObservedObject var viewModel: MyMangaViewModel

  var body: some View {
    CarouselView(itemHeight: 361, views: [
      AnyView(
        Image("imgSample")
          .resizable()
      ),
      AnyView(
        Image("imgSample")
          .resizable()
      ),
      AnyView(
        Image("imgSample")
          .resizable()
      ),
      AnyView(
        Image("imgSample")
          .resizable()
      )
    ])
  }
}
