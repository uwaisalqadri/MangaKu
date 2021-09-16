//
//  LayoutSwitch.swift
//  iosApp
//
//  Created by Uwais Alqadri on 16/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LayoutSwitch: View {
  var body: some View {
    HStack(alignment: .center) {

      Image(systemName: "star.fill")
        .foregroundColor(.black)
        .frame(width: 50, height: 50, alignment: .center)
        .background(
          RoundedRectangle(cornerRadius: 10)
            .foregroundColor(.white)
            .shadow(radius: 5)
        )
        .padding(.trailing, 3)

      Image(systemName: "star.fill")
        .foregroundColor(.black)
        .frame(width: 50, height: 50, alignment: .center)
        .background(
          RoundedRectangle(cornerRadius: 10)
            .foregroundColor(.white)
            .shadow(radius: 5)
        )
        .padding(.leading, 3)

    }
  }
}
