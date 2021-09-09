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

            ACarousel(
              Array(viewModel.mangas.enumerated()), id: \.offset,
              spacing: 20,
              headspace: 50,
              isWrap: true) { index, manga in
              WebImage(url: URL(string: manga.attributes?.posterImage?.original ?? ""))
                .resizable()
                .indicator(.activity)
                .cornerRadius(12)
                .onAppear {
                  position = index
                }
            }.frame(height: 360)
          }
        }

        Spacer()
      }
    }
  }
}
