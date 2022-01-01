//
//  MyMangaView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI
import ACarousel

struct MyMangaView: View {

  @ObservedObject var viewModel: MyMangaViewModel
  let navigator: MyMangaNavigator
  @State var isSlide = true

  private let extensions = Extensions()

  var body: some View {
    GeometryReader { view in
      NavigationView {
        ScrollView(showsIndicators: false) {
          VStack {
            Text("My Manga")
              .font(.custom(.mbold, size: 23))
              .padding(.top, -70)

            LayoutSwitch() { toggle in
              isSlide = toggle
            }.padding(.bottom, 15)

            if !viewModel.isLoading, !viewModel.mangas.isEmpty {

              if isSlide {
                ZStack(alignment: Alignment(horizontal: .center, vertical: .top)) {

                  ACarousel(
                    viewModel.mangas,
                    id: \.self,
                    spacing: 20,
                    headspace: 50,
                    isWrap: true) { manga in

                    WebImage(url: URL(string: extensions.getPosterImage(manga: manga)))
                      .resizable()
                      .indicator(.activity)
                      .cornerRadius(12)
                      .overlay(
                        MyMangaContentView(manga: manga, extensions: extensions)
                      )

                  }.frame(height: view.size.height / 2)
                  .padding(.top, 50)

                }.padding(.top, 5)
              } else {
                LazyVGrid(columns: [
                  GridItem(.adaptive(minimum: 120), spacing: 25, alignment: .center)
                ], alignment: .leading, spacing: 10) {

                  ForEach(viewModel.mangas, id: \.self) { manga in
                    GridItemView(manga: manga, navigator: navigator, extensions: extensions)
                  }

                }.padding(.horizontal, 30)
                .padding(.top, 20)
                .padding(.bottom, 200)
              }

            } else {
              Text("Still Empty Here!")
                .foregroundColor(.black)
                .font(.custom(.sedgwickave, size: 60))
                .multilineTextAlignment(.center)
                .padding(.top, 50)
                .padding(.horizontal, 20)
            }

            Spacer()
          }
        }
      }.onAppear {
        viewModel.fetchFavoriteManga()
      }
    }
  }
}

struct MyMangaContentView: View {

  var manga: Manga
  var extensions: Extensions

  var body: some View {
    ZStack(alignment: .bottomLeading) {

      LinearGradient(gradient: Gradient(colors: [.clear, .black]), startPoint: .top, endPoint: .bottom)
        .cornerRadius(12)

      VStack(alignment: .leading) {

        Text(extensions.getTitle(manga: manga))
          .foregroundColor(.white)
          .font(.custom(.sedgwickave, size: 35))
          .multilineTextAlignment(.leading)
          .lineLimit(2)
          .padding(.bottom, 12)

        Text("Volume \(manga.attributes?.volumeCount ?? 0)")
          .foregroundColor(.white)
          .font(.custom(.mbold, size: 17))
          .padding(.bottom, 5)

        StarsView(manga: manga)

      }.padding(.horizontal, 17)
      .padding(.bottom, 35)
    }
  }
}
