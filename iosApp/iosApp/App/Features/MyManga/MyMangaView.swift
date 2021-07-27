//
//  MyMangaView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDImageSwiftUI

struct MyMangaView: View {

  @Observed var viewModel: MyMangaViewModel

  var body: some View {
    CarouselView(itemHeight: 361, views: [
      AnyView(
        Image("imgSample")
          .resizable()
          .scaledToFill()
      ),
      AnyView(
        Text("imgSample")
      ),
      AnyView(
        Image("imgSample")
          .resizable()
          .scaledToFill()
      )
    ])
  }


  func getViews() -> [AnyView] {
    let views = [AnyView]()
    for manga in viewModel.mangas {
      AnyView(
        WebImage(url: URL(string: manga.attributes.posterImage.original))
          .resizable()
          .scaledToFill()
      )
    }
  }
}

struct MyMangaView_Previews: PreviewProvider {
  static var previews: some View {
    MyMangaView()
  }
}
