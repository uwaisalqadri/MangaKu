//
//  SearchView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct SearchView: View {

  @ObservedObject var viewModel: SearchViewModel
  private let assembler = AppAssembler()

  var body: some View {
    VStack {
      ScrollView(showsIndicators: false) {

        SearchInputView { query in
          viewModel.fetchSearchManga(query: query)
        }

        LazyVGrid(columns: [
          GridItem(.adaptive(minimum: 90), spacing: 25, alignment: .center)
        ], alignment: .leading, spacing: 10) {

          if viewModel.isLoading {
            ForEach(0..<12) { _ in
              ShimmerSearchView()
            }
          } else {
            ForEach(viewModel.mangas, id: \.id) { manga in
              NavigationLink(destination: DetailView(viewModel: assembler.resolve(),mangaViewModel: assembler.resolve() , mangaId: manga.id)) {
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
