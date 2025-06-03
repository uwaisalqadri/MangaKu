//
//  BrowseView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared

struct BrowseView: View {
  @StateObject private var viewModel: BrowseViewModel
  @State private var isLoaded = false
  
  private let genres: [Genre] = [
    Genre(name: "Shonen", image: "imgShonen", query: "shonen", font: .sedgwickave),
    Genre(name: "Seinen", image: "imgSeinen", query: "seinen", font: .mashanzheng),
    Genre(name: "Shojo", image: "imgShojo", query: "shojo", font: .sedgwickave)
  ]
  
  init() {
    _viewModel = StateObject(wrappedValue: BrowseViewModel())
  }

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
 
        if viewModel.state.isLoading {
          VStack {
            ForEach(0..<10) { _ in
              ShimmerBrowseView()
            }
          }
          .padding(.leading, 17)
          .padding(.trailing, 30)
          .padding(.bottom, 100)

        } else {
          VStack {
            ForEach(viewModel.state.items, id: \.id) { manga in
              NavigationLink(destination: DetailView(mangaId: manga.id)) {
                MangaRow(manga: manga)
              }.buttonStyle(.plain)
            }
          }
          .padding(.leading, 17)
          .padding(.trailing, 30)
          .padding(.bottom, 100)
        }
      }.padding(.top, 30)
    }
    .navigationBarTitle("Browse")
//    .navigationBarItems(
//      trailing: NavigationLink(destination: navigator.routeToSearch()) {
//        Image(systemName: "magnifyingglass")
//          .resizable()
//          .foregroundColor(.black)
//          .frame(width: 20, height: 20)
//      }
//    )
    .onAppear {
      viewModel.send(action: .getMangas)
    }
  }
}
