//
//  SearchPageView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared
import KMMViewModelSwiftUI

struct SearchPageView: View {
  
  @StateViewModel var viewModel = SearchViewModel()
  @State var searchQuery: String = ""
  let navigator: SearchRouter
  
  var body: some View {
    VStack {
      if case .empty = onEnum(of: viewModel.searchManga) {
        Text("Result Not Found, Search Something Else :)")
          .foregroundColor(.black)
          .font(.custom(.sedgwickave, size: 60))
          .multilineTextAlignment(.center)
        
      } else {
        ScrollView(showsIndicators: false) {
          LazyVGrid(columns: [
            GridItem(.adaptive(minimum: 90), spacing: 25, alignment: .center)
          ], alignment: .leading, spacing: 10) {
            
            if case .loading = onEnum(of: viewModel.searchManga) {
              ForEach(0..<12) { _ in
                ShimmerSearchView()
              }
              
            } else if case .success(let result) = onEnum(of: viewModel.searchManga),
                      let items = result.data?.compactMap({ $0 as? Manga }) {
              ForEach(items, id: \.id) { manga in
                NavigationLink(destination: navigator.routeToDetail(mangaId: manga.id)) {
                  SearchRow(manga: manga)
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
        viewModel.getSearchManga(query: searchQuery.isEmpty ? "naruto" : searchQuery)
      }
  }
}
