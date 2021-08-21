//
//  ShimmerItemView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 21/08/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct ShimmerItemView: View {
  var body: some View {
    HStack {
      ShimmerView()
        .frame(width: 124, height: 200)
        .cornerRadius(12)

      VStack(alignment: .leading) {

        ShimmerView()
          .frame(height: 30)
          .cornerRadius(12)
          .padding(.top, 5)

        HStack {
          ShimmerView()
            .frame(width: 80, height: 20)
            .cornerRadius(12)

          ShimmerView()
            .frame(width: 60, height: 20)
            .cornerRadius(12)
        }.padding(.top, 5)

        Spacer(minLength: 30)

        ShimmerView()
          .frame(width: 100, height: 40)
          .cornerRadius(9)
          .padding(.bottom, 10)
      }.padding(.leading, 15)

      Spacer()

    }.padding(.bottom, 30)
  }
}
