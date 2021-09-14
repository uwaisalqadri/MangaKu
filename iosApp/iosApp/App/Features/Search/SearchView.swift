//
//  SearchView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SearchView: View {

  @ObservedObject var viewModel: SearchViewModel
  private let assembler = AppAssembler()

  var body: some View {
    VStack {
      ScrollView(showsIndicators: false) {

        // MARK: Search Bar
        HStack {
          Image(systemName: "magnifyingglass")
            .resizable()
            .foregroundColor(.init(.darkGray))
            .frame(width: 20, height: 20)
            .padding(.leading, 30)

          TextField("Search Manga...", text: $viewModel.searchQuery)
            .foregroundColor(.init(.darkGray))
            .font(.custom(.mmedium, size: 16))
            .frame(height: 40)
            .autocapitalization(.none)
            .disableAutocorrection(.none)
            .padding(.leading, 13)
            .padding(.trailing, 30)

        }.background(Color.init(.systemGray6))
        .cornerRadius(20)
        .padding([.horizontal, .top], 30)

        LazyVGrid(columns: [
          GridItem(.adaptive(minimum: 90), spacing: 25, alignment: .center)
        ], alignment: .leading, spacing: 10) {

          withAnimation(.interactiveSpring()) {
            ForEach(viewModel.mangas, id: \.id) { manga in
              NavigationLink(destination: DetailView(viewModel: assembler.resolve(), mangaId: manga.id)) {
                SearchItemView(manga: manga)
              }.buttonStyle(PlainButtonStyle())
            }
          }

        }.padding(.horizontal, 30)
        .padding(.top, 20)

        Spacer(minLength: 250)

      }
    }.navigationTitle("Search")
  }
}
