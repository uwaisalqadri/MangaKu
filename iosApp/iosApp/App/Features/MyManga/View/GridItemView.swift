//
//  GridItemView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 17/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI

struct GridItemView: View {

  let manga: Manga
  let assembler: Assembler
  let extensions: Extensions

  var body: some View {
    ZStack(alignment: .topLeading) {
      WebImage(url: URL(string: extensions.getPosterImage(manga: manga)))
        .resizable()
        .indicator(.activity)
        .clipped()
        .frame(width: 119, height: 177)

      VStack(alignment: .leading) {

        Spacer()

        HStack(alignment: .top) {
          Spacer()

          NavigationLink(destination: DetailView(viewModel: assembler.resolve(), mangaId: manga.id)) {
            VStack(alignment: .leading) {
              Text(extensions.getTitle(manga: manga))
                .lineLimit(2)
                .font(.custom(.mbold, size: 17))
                .padding(.top, 20)

              Text("Volume \(manga.attributes?.volumeCount ?? 0)")
                .foregroundColor(.init(.darkGray))
                .font(.custom(.msemibold, size: 10))

              Spacer()

              HStack {
                Text("Read More")
                  .foregroundColor(.black)
                  .font(.custom(.mbold, size: 13))

                Image(systemName: "chevron.right")
                  .foregroundColor(.black)
                  .frame(width: 6, height: 6)
              }.padding(.bottom, 15)

            }.frame(width: 118, height: 107)
            .background(
              RoundedRectangle(cornerRadius: 5)
                .foregroundColor(.white)
                .shadow(radius: 7)
            )
          }.buttonStyle(PlainButtonStyle())
        }
      }
    }.frame(height: 220)
    .padding(.bottom, 40)
  }
}
