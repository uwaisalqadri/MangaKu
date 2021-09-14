//
//  DetailView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI

struct DetailView: View {

  @ObservedObject var viewModel: DetailViewModel
  let mangaId: String

  var body: some View {
    NavigationView {
      VStack(alignment: .leading) {
        ScrollView(showsIndicators: false) {

          WebImage(url: URL(string: viewModel.manga?.attributes?.coverImage?.original ?? ""))
            .resizable()
            .indicator(.activity)
            .clipped()
            .frame(height: 200)
            .cornerRadius(10)
            .padding(.horizontal, 24)

          if !viewModel.loading {
            VStack(alignment: .leading) {
              Text(viewModel.manga?.getTitle() ?? "")
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

                  Text("\(viewModel.manga?.attributes?.averageRating ?? 0.0)")
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
        }
      }

    }.navigationBarTitle("Detail")
    .onAppear {
      viewModel.fetchManga(mangaId: mangaId)
    }
  }
}
