//
//  BrowseView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct BrowseView: View {

  @ObservedObject var viewModel: BrowseViewModel

  var body: some View {
    NavigationView {
      ScrollView(.vertical, showsIndicators: false) {
        VStack(alignment: .leading) {
          Text("Genre")
            .font(.custom(.msemibold, size: 15))
            .padding(.horizontal, 15)

          ScrollView(.horizontal, showsIndicators: false) {
            HStack {
              ForEach(0..<5) { _ in
                GenreView()
              }
            }.padding(.leading, 10)
          }

          Text("Last Update")
            .font(.custom(.msemibold, size: 15))
            .padding([.horizontal, .top], 30)
          VStack {
            ForEach(viewModel.mangas, id: \.id) { manga in
              MangaItemView(manga: manga)
            }
          }.padding(.horizontal, 30)
        }.padding(.top, 30)
      }
      .navigationBarTitle("Browse")
      .navigationBarItems(
        trailing: Button(action: {
          print("search")
        }) {
          Image(systemName: "magnifyingglass")
            .resizable()
            .foregroundColor(.black)
            .frame(width: 20, height: 20)
        }
      )
    }.onAppear {
      viewModel.fetchMangas()
    }
  }
}
