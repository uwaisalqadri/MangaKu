//
//  SearchView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared

struct SearchView: View {
  @StateObject var viewModel: SearchViewModel
  @State var searchQuery: String = ""
  
  init() {
    _viewModel = StateObject(wrappedValue: SearchViewModel())
  }
  
  var body: some View {
    VStack {
      if viewModel.state.items.isEmpty {
        Text("Result Not Found, Search Something Else :)")
          .foregroundColor(.black)
          .font(.custom(.sedgwickave, size: 60))
          .multilineTextAlignment(.center)
        
      } else {
        ScrollView(showsIndicators: false) {
          LazyVGrid(columns: [
            GridItem(.adaptive(minimum: 90), spacing: 25, alignment: .center)
          ], alignment: .leading, spacing: 10) {
            if viewModel.state.isLoading {
              ForEach(0..<12) { _ in
                ShimmerSearchView()
              }
            } else {
              ForEach(viewModel.state.items, id: \.id) { manga in
                NavigationLink(destination: DetailView(mangaId: manga.id)) {
                  SearchRow(manga: manga)
                }.buttonStyle(PlainButtonStyle())
              }
            }
          }.padding(.horizontal, 30)
            .padding(.top, 20)
          
          Spacer(minLength: 250)
        }
      }
    }
    .navigationTitle("Search")
    .searchable(text: $searchQuery, placement: .navigationBarDrawer(displayMode: .always))
    .autocapitalization(.none)
    .disableAutocorrection(true)
    .onChange(of: searchQuery) { query in
      viewModel.send(action: .getManga(query: query))
    }
    .onSubmit(of: .search) {
      viewModel.send(action: .getManga(query: searchQuery.isEmpty ? "naruto" : searchQuery))
    }
  }
}
