//
//  MangaItemView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI

struct MangaItemView: View {

  let manga: Manga
  var onReadMoreListener: ((Manga) -> Void)?

  var body: some View {
    HStack {
      WebImage(url: URL(string: manga.attributes?.posterImage?.original ?? ""))
        .resizable()
        .indicator(.activity)
        .frame(width: 124, height: 200)
        .cornerRadius(12)

      VStack(alignment: .leading) {
        StarsView()

        Text(manga.getTitle())
          .font(.custom(.mbold, size: 18))
          .lineLimit(2)
          .padding(.top, 5)

        HStack {
          Text(manga.attributes?.startDate ?? "")
            .font(.custom(.mbold, size: 12))
            .foregroundColor(.secondary)

          Text("Vol.\(manga.attributes?.volumeCount ?? "0")")
            .font(.custom(.mbold, size: 15))
        }.padding(.top, 5)

        Spacer(minLength: 30)

        Button(action: {
          onReadMoreListener?(manga)
        }, label: {
          Text("Read Now")
            .foregroundColor(.white)
            .font(.custom(.mbold, size: 15))
            .padding(.horizontal, 19)
            .padding(.vertical, 8)
        })
        .background(Color.black)
        .cornerRadius(9)
        .padding(.bottom, 10)
      }.padding(.leading, 15)

      Spacer()

    }.padding(.bottom, 30)
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
