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
  @State var searchQuery: String = ""
  let navigator: SearchNavigator

  var body: some View {
    VStack {
      if case .empty = viewModel.listManga {
        Text("Result Not Found, Search Something Else :)")
          .foregroundColor(.black)
          .font(.custom(.sedgwickave, size: 60))
          .multilineTextAlignment(.center)

      } else {
        ScrollView(showsIndicators: false) {
          LazyVGrid(columns: [
            GridItem(.adaptive(minimum: 90), spacing: 25, alignment: .center)
          ], alignment: .leading, spacing: 10) {

            if case .loading = viewModel.listManga {
              ForEach(0..<12) { _ in
                ShimmerSearchView()
              }

            } else if case .success(let data) = viewModel.listManga {
              ForEach(data, id: \.id) { manga in
                NavigationLink(destination: navigator.navigateToDetailView(mangaId: manga.id)) {
                  SearchItemView(manga: manga)
                }.buttonStyle(PlainButtonStyle())
              }

            }

          }.padding(.horizontal, 30)
          .padding(.top, 20)

          Spacer(minLength: 250)

        }
      }
    }.navigationTitle("Search")
    .searchable(text: $searchQuery, placement: .navigationBarDrawer(displayMode: .always))
    .autocapitalization(.none)
    .disableAutocorrection(true)
      // MARK: disable this for the meantime, it's laggy as hell
      // .onChange(of: searchQuery) { query in
      //   viewModel.fetchSearchManga(query: query.isEmpty ? "naruto" : query)
      // }
    .onSubmit(of: .search) {
      viewModel.fetchSearchManga(query: searchQuery.isEmpty ? "naruto" : searchQuery)
    }
    .onAppear {
      viewModel.fetchSearchManga(query: "naruto")
    }
  }
}
