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
    NavigationView {
      VStack {
        Text("My Manga")
          .font(.custom(.mbold, size: 23))
          .padding(.top, -70)
          .padding(.bottom, 30)

        ZStack(alignment: Alignment(horizontal: .center, vertical: .top)) {
          Text("Naruto")
            .font(.custom(.sedgwickave, size: 130))
            .padding(.top, -60)

          MangaCarouselView(itemHeight: 361, views: [
            AnyView(
              WebImage(url: URL(string: "https://media.kitsu.io/manga/poster_images/14/original.jpg?1434249426"))
                .resizable()
            ),
            AnyView(
              WebImage(url: URL(string: "https://media.kitsu.io/manga/poster_images/13/original.jpg?1434249424"))
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
          .padding(.top, -180)
        }
      }
    }.onAppear {
      viewModel.fetchMangas()
      if viewModel.loading {
        print("loading..")
      } else {
        print(viewModel.mangas[0].attributes?.posterImage?.original ?? "")
      }
    }
  }
}
