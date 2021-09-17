//
//  DetailView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI
import KotlinCore

struct DetailView: View {

  @ObservedObject var viewModel: DetailViewModel
  @ObservedObject var mangaViewModel: MyMangaViewModel
  let mangaId: String

  private let extensions = Extensions()

  var body: some View {
    VStack(alignment: .leading) {

      ScrollView(showsIndicators: false) {

        if viewModel.loading || viewModel.manga == nil {
          ShimmerDetailView()
        } else {
          WebImage(url: URL(string: extensions.getCoverImage(manga: viewModel.manga ?? Manga(attributes: nil, id: "", type: ""))))
            .resizable()
            .indicator(.activity)
            .clipped()
            .frame(height: 200)
            .cornerRadius(10)
            .padding(.horizontal, 24)

          VStack(alignment: .leading) {
            Text(extensions.getTitle(manga: viewModel.manga ?? Manga(attributes: nil, id: "", type: "")))
              .foregroundColor(.black)
              .font(.custom(.mbold, size: 25))
              .padding(.top, 15)

            Text("Action, Adventure")
              .foregroundColor(.black)
              .font(.custom(.mmedium, size: 15))

            HStack {

              Text(viewModel.manga?.attributes?.startDate ?? "")
                .foregroundColor(.white)
                .font(.custom(.mbold, size: 13))
                .padding(10)
                .background(Color.black)
                .cornerRadius(5)

              HStack {
                Image(systemName: "star.fill")
                  .foregroundColor(.yellow)
                  .frame(width: 15, height: 15)

                Text(String(viewModel.manga?.attributes?.averageRating ?? 0.0).removeCharacters(from: "0"))
                  .foregroundColor(.black)
                  .font(.custom(.msemibold, size: 14))

              }.padding(.leading, 5)

            }.padding(.top, 10)

            Text("Description")
              .foregroundColor(.black)
              .font(.custom(.mbold, size: 21))
              .padding(.top, 50)

            Text(viewModel.manga?.attributes?.synopsis ?? "")
              .foregroundColor(.black)
              .font(.custom(.mmedium, size: 15))
              .padding(.top, 15)

            Spacer(minLength: 400)

          }.padding(.horizontal, 30)
        }

      }.padding(.top, 30)

    }.navigationTitle("Detail")
    .navigationBarItems(trailing: Button(action: {
      mangaViewModel.addFavoriteManga(manga: viewModel.manga ?? Manga(attributes: nil, id: "", type: "")) {
        print("SAVED")
      }
    }) {
      Image(systemName: "heart")
        .resizable()
        .foregroundColor(.red)
        .frame(width: 22, height: 20)
    })
    .onAppear {
      viewModel.fetchManga(mangaId: mangaId)
    }
  }
}
