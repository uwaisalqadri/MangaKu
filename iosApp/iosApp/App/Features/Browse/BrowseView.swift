//
//  BrowseView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct BrowseView: View {

  @ObservedObject var viewModel: BrowseViewModel
  let navigator: BrowseNavigator

  let genres: [Genre] = [
    Genre(name: "Shonen", image: "imgShonen", query: "shonen", font: .sedgwickave),
    Genre(name: "Seinen", image: "imgSeinen", query: "seinen", font: .mashanzheng),
    Genre(name: "Shojo", image: "imgShojo", query: "shojo", font: .sedgwickave)
  ]

  var body: some View {
    ScrollView(.vertical, showsIndicators: false) {
      VStack(alignment: .leading) {
        Text("Genre")
          .font(.custom(.msemibold, size: 15))
          .padding(.horizontal, 17)

        ScrollView(.horizontal, showsIndicators: false) {
          HStack {
            ForEach(genres, id: \.id) { genre in
              GenreView(genre: genre)
            }
          }.padding(.leading, 13)
        }

        Text("Trending Now")
          .font(.custom(.msemibold, size: 15))
          .padding(.leading, 17)
          .padding(.top, 30)


        if case .loading = viewModel.trendingManga {
          VStack {
            ForEach(0..<10) { _ in
              ShimmerBrowseView()
            }
          }.padding(.leading, 17)
          .padding(.trailing, 30)
          .padding(.bottom, 100)

        } else if case .success(let data) = viewModel.trendingManga {
          VStack {
            ForEach(data, id: \.id) { manga in
              NavigationLink(destination: navigator.navigateToDetailView(mangaId: manga.id)) {
                MangaItemView(manga: manga)
              }.buttonStyle(PlainButtonStyle())
            }
          }.padding(.leading, 17)
          .padding(.trailing, 30)
          .padding(.bottom, 100)
        }
      }.padding(.top, 30)
    }
    .navigationBarTitle("Browse")
    .navigationBarItems(
      trailing: NavigationLink(destination: navigator.navigateToSearchView()) {
        Image(systemName: "magnifyingglass")
          .resizable()
          .foregroundColor(.black)
          .frame(width: 20, height: 20)
      }
    )
  }
}
