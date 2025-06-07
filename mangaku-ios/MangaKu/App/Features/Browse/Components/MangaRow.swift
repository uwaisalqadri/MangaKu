//
//  MangaRow.swift
//  iosApp
//
//  Created by Uwais Alqadri on 26/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct MangaRow: View {

  let manga: Manga

  var body: some View {
    HStack {
      WebImage(url: URL(string: manga.getPosterImage()))
        .resizable()
        .indicator(.activity)
        .frame(width: 124, height: 200)
        .cornerRadius(12)

      VStack(alignment: .leading) {
        StarsView(manga: manga)

        Text(manga.getTitle())
          .font(.custom(.mbold, size: 18))
          .lineLimit(2)
          .padding(.top, 5)

        HStack {
          Text(DateFormatterKt.formatDate(dateString: manga.startDate, format: Shared.DateFormatter().CASUAL_DATE_FORMAT))
            .font(.custom(.mbold, size: 12))
            .foregroundColor(.secondary)

          Text("Ch.\(manga.chapterCount)")
            .font(.custom(.mbold, size: 15))
        }.padding(.top, 5)

        Spacer(minLength: 30)

        Text("Read More")
          .foregroundColor(.white)
          .font(.custom(.mbold, size: 15))
          .padding(13)
          .background(Color.black)
          .cornerRadius(9)
          .padding(.bottom, 10)

      }.padding(.leading, 15)

      Spacer()

    }.padding(.bottom, 30)
  }
}

struct StarsView: View {

  let manga: Manga
  @State var averageRating: Double = 0

  var body: some View {
    HStack {
      ForEach(0..<5) { index in
        Image(systemName: index <= Int32(averageRating) ? "star.fill" : "star")
          .resizable()
          .frame(width: 15, height: 15)
          .foregroundColor(.yellow)
          .onAppear {
            averageRating = manga.averageRating
          }
      }
    }
  }
}
