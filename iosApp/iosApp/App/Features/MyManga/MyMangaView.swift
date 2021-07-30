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
  var views = [AnyView]()

  var body: some View {
    CarouselView(itemHeight: 361, views: views)
  }


  func getViews() {
    viewModel.mangas.forEach { manga in
      AnyView(
        WebImage(url: URL(string: manga.attributes.posterImage.original))
          .resizable()
          .scaledToFill()
      )
    }
  }
}

struct MyMangaView_Previews: PreviewProvider {
  static let assembler = AppAssembler()
  static var previews: some View {
    MyMangaView(viewModel: assembler.resolve())
  }
}
