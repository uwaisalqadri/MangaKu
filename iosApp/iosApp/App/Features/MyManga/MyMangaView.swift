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
import ACarousel

struct MyMangaView: View {

  @ObservedObject var viewModel: MyMangaViewModel
  private let extensions = Extensions()

  var body: some View {
    GeometryReader { view in
      NavigationView {
        VStack {
          Text("My Manga")
            .font(.custom(.mbold, size: 23))
            .padding(.top, -70)

          LayoutSwitch()
            .padding(.bottom, 15)

          if !viewModel.loading, !viewModel.mangas.isEmpty {
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
          }

          Spacer()
        }
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
