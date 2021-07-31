//
//  GenreView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct Genre {
  let id = UUID()
  let name: String
  let image: String
  let query: String
  let font: String
}

struct GenreView: View {

  let genre: Genre

  var body: some View {
    ZStack {
      Image(genre.image)
        .resizable()
        .frame(width: 137, height: 90)
        .cornerRadius(11)
        .shadow(radius: 2)
        .overlay(
          Rectangle()
            .cornerRadius(11)
            .foregroundColor(.white)
            .opacity(0.5)
        )

      Text(genre.name)
        .font(.custom(genre.font, size: 30))
        .foregroundColor(.black)
        .padding(.top, 20)
        .padding(.bottom, 5)
    }
  }
}
