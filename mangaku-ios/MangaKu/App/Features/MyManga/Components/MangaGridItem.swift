//
//  MangaGridItem.swift
//  iosApp
//
//  Created by Uwais Alqadri on 17/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct MangaGridItem: View {

  let manga: Manga

  var body: some View {
    ZStack(alignment: .topLeading) {
      WebImage(url: URL(string: manga.posterImage.url))
        .resizable()
        .indicator(.activity)
        .clipped()
        .frame(width: 119, height: 177)

      VStack(alignment: .leading) {

        Spacer()

        HStack(alignment: .top) {
          Spacer()

          VStack(alignment: .leading) {
            Text(manga.title)
              .lineLimit(2)
              .font(.custom(.mbold, size: 17))
              .padding(.top, 20)
              .padding(.horizontal, 5)

            Text("Volume \(manga.volumeCount)")
              .foregroundColor(.init(.darkGray))
              .font(.custom(.msemibold, size: 10))
              .padding(.horizontal, 5)

            Spacer()

            HStack {
              Text("Read More")
                .foregroundColor(.black)
                .font(.custom(.mbold, size: 13))

              Image(systemName: "chevron.right")
                .foregroundColor(.black)
                .frame(width: 6, height: 6)
            }.padding(.bottom, 15)
            .padding(.horizontal, 5)

          }.frame(width: 118, height: 107)
          .background(
            RoundedRectangle(cornerRadius: 5)
              .foregroundColor(.white)
              .shadow(radius: 7)
          )
        }
      }
    }.frame(height: 220)
    .padding(.bottom, 40)
  }
}
