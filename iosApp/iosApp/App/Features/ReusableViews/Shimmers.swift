//
//  Shimmers.swift
//  iosApp
//
//  Created by Uwais Alqadri on 21/08/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct ShimmerBrowseView: View {
  var body: some View {
    HStack {
      ShimmerView(isAnimating: false)
        .frame(width: 124, height: 200)
        .cornerRadius(12)

      VStack(alignment: .leading) {

        ShimmerView(isAnimating: false)
          .frame(height: 30)
          .cornerRadius(12)
          .padding(.top, 5)

        HStack {
          ShimmerView(isAnimating: false)
            .frame(width: 80, height: 20)
            .cornerRadius(12)

          ShimmerView(isAnimating: false)
            .frame(width: 60, height: 20)
            .cornerRadius(12)
        }.padding(.top, 5)

        Spacer(minLength: 30)

        ShimmerView(isAnimating: false)
          .frame(width: 100, height: 40)
          .cornerRadius(9)
          .padding(.bottom, 10)
      }.padding(.leading, 15)

      Spacer()

    }.padding(.bottom, 30)
  }
}


struct ShimmerSearchView: View {

  var body: some View {
    ShimmerView()
      .frame(height: 140)
      .cornerRadius(8)
      .padding(.bottom, 20)
  }
}

struct ShimmerDetailView: View {

  var body: some View {
    VStack(alignment: .leading) {
      ShimmerView()
        .frame(height: 200)
        .cornerRadius(10)
        .padding(.horizontal, 24)

      VStack(alignment: .leading) {
        ShimmerView()
          .frame(height: 40)
          .cornerRadius(8)
          .padding(.leading, 24)
          .padding(.trailing, 80)

        ShimmerView()
          .frame(height: 25)
          .cornerRadius(8)
          .padding(.leading, 24)
          .padding(.trailing, 50)

        HStack {

          ShimmerView()
            .frame(width: 100, height: 30)
            .cornerRadius(8)
            .padding(.leading, 24)

          HStack {
            Image(systemName: "star.fill")
              .foregroundColor(.yellow)
              .frame(width: 15, height: 15)

            ShimmerView()
              .frame(width: 100, height: 20)
              .cornerRadius(8)
              .padding(.leading, 7)
          }.padding(.leading, 5)

        }.padding(.top, 10)

        ShimmerView()
          .frame(height: 40)
          .cornerRadius(8)
          .padding(.leading, 24)
          .padding(.trailing, 100)
          .padding(.top, 50)

        ForEach(0..<10) { _ in

          ShimmerView()
            .frame(height: 20)
            .cornerRadius(8)
            .padding(.vertical, -15)

        }.padding(.horizontal, 24)
        .padding(.top, 30)

        Spacer(minLength: 400)
      }
    }
  }
}










