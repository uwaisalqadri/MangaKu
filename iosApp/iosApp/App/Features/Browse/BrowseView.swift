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
    Text(String(viewModel.mangas.count))
      .onAppear {
        viewModel.fetchMangas()
        print("onAppear")
      }
  }
}

struct BrowseView_Previews: PreviewProvider {
  static let assembler = AppAssembler()
  static var previews: some View {
    BrowseView(viewModel: assembler.resolve())
  }
}
