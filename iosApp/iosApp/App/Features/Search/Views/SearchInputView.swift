//
//  SearchInputView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 12/12/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import SwiftUI

struct SearchInputView: View {

  @State var query = ""
  var onQueryChange: ((String) -> Void)?

  var body: some View {
    HStack {
      Image(systemName: "magnifyingglass")
        .resizable()
        .foregroundColor(.init(.darkGray))
        .frame(width: 20, height: 20)
        .padding(.leading, 30)

      TextField("Search Manga...", text: $query, onCommit: {
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
          onQueryChange?(query)
        }
      })
      .foregroundColor(.init(.darkGray))
      .font(.custom(.mmedium, size: 16))
      .frame(height: 40)
      .autocapitalization(.none)
      .disableAutocorrection(true)
      .padding(.leading, 13)
      .padding(.trailing, 30)
      .onChange(of: query) { text in
        onQueryChange?(text)
      }

    }.background(Color.init(.systemGray6))
    .cornerRadius(20)
    .padding([.horizontal, .top], 30)
  }
}
