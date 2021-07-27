//
//  GenreView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

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
        .font(.custom(.sedgwickave, size: 30))
        .foregroundColor(.black)
        .padding(.top, 20)
        .padding(.bottom, 5)
    }
  }
}


struct Genre {
  let name: String
  let image: UIImage
  let query: String
  let font: String
}
