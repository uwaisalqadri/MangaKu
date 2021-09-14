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
  @State var query: String = ""

  var body: some View {
    NavigationView {
      VStack {
        ScrollView(showsIndicators: false) {

          SearchField(query: query)
            .background(Color.init(.systemGray6))
            .cornerRadius(20)
            .padding(.top, -65)
            .padding(.horizontal, 30)

          LazyVGrid(columns: [
            GridItem(.adaptive(minimum: 90), spacing: 25, alignment: .center)
          ], alignment: .leading, spacing: 10) {
            ForEach(0..<10) { index in
              SearchItemView()
            }
          }.padding(.horizontal, 30)
          .padding(.top, 20)

        }
      }
    }.navigationBarTitle("Search")
  }
}

struct SearchField: View {

  @State var query: String

  var body: some View {

    HStack {
      Image(systemName: "magnifyingglass")
        .resizable()
        .foregroundColor(.init(.darkGray))
        .frame(width: 20, height: 20)
        .padding(.leading, 30)

      TextField("Search Manga...", text: $query)
        .foregroundColor(.init(.darkGray))
        .font(.custom(.mmedium, size: 16))
        .frame(height: 40)
        .padding(.leading, 13)
        .padding(.trailing, 30)

    }
  }
}
