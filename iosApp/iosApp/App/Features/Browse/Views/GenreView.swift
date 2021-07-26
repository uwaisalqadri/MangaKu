//
//  GenreView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct Genre {
  let name: String
  let image: String
  let query: String
}

struct GenreView: View {
  var body: some View {
    ZStack {
      Image("imgGenreSample")
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

      Text("Shonen")
        .font(.system(size: 25, weight: .bold))
        .foregroundColor(.black)
        .padding(.top, 17)
        .padding(.bottom, 5)
    }
  }
}
