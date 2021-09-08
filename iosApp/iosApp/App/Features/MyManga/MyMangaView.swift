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

//          MangaCarouselView(itemHeight: 361, views: [
//            AnyView(
//              WebImage(url: URL(string: viewModel.mangas[0].attributes?.posterImage?.original ?? ""))
//                .resizable()
//            ),
//            AnyView(
//              WebImage(url: URL(string: viewModel.mangas[1].attributes?.posterImage?.original ?? ""))
//                .resizable()
//            ),
//            AnyView(
//              WebImage(url: URL(string: viewModel.mangas[2].attributes?.posterImage?.original ?? ""))
//                .resizable()
//            )
//          ]).padding(.top, -180)
        }
      }
    }.onAppear {
      viewModel.fetchFavoriteManga()
      show()
    }
  }

  func show() {
    viewModel.loading ? print("loading") : print("view", viewModel.mangas)
  }
}
