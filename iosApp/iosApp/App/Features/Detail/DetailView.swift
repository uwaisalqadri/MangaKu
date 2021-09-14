//
//  DetailView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct DetailView: View {

  @ObservedObject var viewModel: DetailViewModel
  let mangaId: String

  var body: some View {
    NavigationView {
      ScrollView(showsIndicators: false) {
        VStack(alignment: .leading) {

          Image("imgSample")
            .resizable()
            .frame(height: 200)
            .padding(.horizontal, 24)
            .cornerRadius(10)

          VStack(alignment: .leading) {
            Text("My Hero Academia")
              .foregroundColor(.black)
              .font(.custom(.mbold, size: 25))

            Text("Action, Adventure")
              .foregroundColor(.black)
              .font(.custom(.mmedium, size: 15))

            HStack {

              Text("Sep 17, 2013")
                .foregroundColor(.white)
                .font(.custom(.mbold, size: 13))
                .padding(10)
                .background(Color.black)
                .cornerRadius(5)

              HStack {
                Image(systemName: "star.fill")
                  .foregroundColor(.yellow)
                  .frame(width: 15, height: 15)

                Text("4.48")
                  .foregroundColor(.black)
                  .font(.custom(.msemibold, size: 14))
                  .padding(.leading, 5)
              }

            }.padding(.top, 10)

            Text("Description")
              .foregroundColor(.black)
              .font(.custom(.mbold, size: 21))
              .padding(.top, 50)

            Text("Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with...  Read More")
              .foregroundColor(.black)
              .font(.custom(.mmedium, size: 15))
              .padding(.top, 15)

          }.padding(.horizontal, 30)

        }
      }
    }.navigationBarTitle("Detail")
    .onAppear {
      viewModel.fetchManga(mangaId: mangaId)
    }
  }
}
