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
  @ObservedObject var itemView = ItemView()
  @State var position = 0

  var body: some View {
    NavigationView {
      VStack {
        Text("My Manga")
          .font(.custom(.mbold, size: 23))
          .padding(.top, -70)
          .padding(.bottom, 30)

        if !viewModel.loading, !viewModel.mangas.isEmpty {
          ZStack(alignment: Alignment(horizontal: .center, vertical: .top)) {
            Text(viewModel.mangas[position].getTitle())
              .font(.custom(.sedgwickave, size: 70))
              .lineLimit(2)
              .multilineTextAlignment(.center)
              .padding(.top, -60)
              .onAppear {
                print("POSITION", position)
              }

            MangaCarouselView(itemHeight: 361, views: itemView.views).padding(.top, -180)

          }.onAppear {
            if !viewModel.loading {
              print("view before", itemView.views)
              viewModel.mangas.enumerated().forEach { index, manga in
                position = index
                print("POSITION", index)
                itemView.strings.insert(manga.getTitle(), at: index)
                itemView.views.insert(AnyView(
                    WebImage(url: URL(string: manga.attributes?.posterImage?.original ?? ""))
                      .resizable()
                ), at: index)

                print("view after", itemView.views)
              }
            }
          }
        }
      }
    }
  }
}

class ItemView: ObservableObject {
  @Published var views = [AnyView]()
  @Published var strings = [String]()
}
