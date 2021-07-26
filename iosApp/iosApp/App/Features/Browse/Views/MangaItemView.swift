//
//  MangaItemView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct MangaItemView: View {
  var body: some View {
    HStack {
      Image("imgSample")
        .resizable()
        .frame(width: 124, height: 200)
        .cornerRadius(12)

      VStack(alignment: .leading) {
        StarsView()

        Text("Boku No Hero Academia")
          .font(.system(size: 20, weight: .bold))
          .padding(.top, 5)

        HStack {
          Text("04-05-2019")
            .font(.system(size: 12, weight: .bold))
            .foregroundColor(.secondary)

          Text("Vol.25")
            .font(.system(size: 18, weight: .bold))
        }.padding(.top, 5)

        Spacer(minLength: 60)

        Button(action: {
          print("Read Now")
        }, label: {
          Text("Read Now")
            .foregroundColor(.white)
            .font(.system(size: 15, weight: .bold))
            .padding(.horizontal, 19)
            .padding(.vertical, 8)
        })
        .background(Color.black)
        .cornerRadius(9)
      }.padding(.horizontal, 20)
      .padding(.vertical, 15)
    }
  }
}

struct StarsView: View {
  var body: some View {
    HStack {
      ForEach(0..<5) { item in
        Image(systemName: "star.fill")
          .resizable()
          .frame(width: 15, height: 15)
          .foregroundColor(.yellow)
      }
    }
  }
}
