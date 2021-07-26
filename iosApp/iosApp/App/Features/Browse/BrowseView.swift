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
    ScrollView(.vertical, showsIndicators: false) {
      VStack(alignment: .leading) {
        Text("Genre")
          .font(.system(size: 15, weight: .regular))

        ScrollView(.horizontal, showsIndicators: false) {
          HStack {
            ForEach(0..<5) { _ in
              GenreView()
            }
          }
        }

        Text("Last Update")
          .font(.system(size: 15, weight: .regular))
        VStack {
          ForEach(viewModel.mangas, id: \.id) { manga in
            MangaItemView()
          }
        }
      }.padding(.horizontal, 7)
      .onAppear {
        viewModel.fetchMangas()
    }
    }
  }
}

struct BrowseView_Previews: PreviewProvider {
  static let assembler = AppAssembler()
  static var previews: some View {
    BrowseView(viewModel: assembler.resolve())
  }
}
