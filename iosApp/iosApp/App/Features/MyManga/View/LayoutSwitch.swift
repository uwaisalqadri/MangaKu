//
//  LayoutSwitch.swift
//  iosApp
//
//  Created by Uwais Alqadri on 16/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct LayoutSwitch: View {

  @State var isSlide = true
  var switchHandler: (Bool) -> Void

  var body: some View {
    HStack(alignment: .center) {

      Button(action: {
        isSlide.toggle()
        switchHandler(isSlide)
      }) {
        Image("icSlide")
          .resizable()
          .padding(6)
          .foregroundColor(.black)
          .frame(width: 50, height: 50, alignment: .center)
          .background(
            RoundedRectangle(cornerRadius: 10)
              .foregroundColor(isSlide ? .init(.systemGray6) : .white)
              .shadow(radius: 5)
          )
          .padding(.trailing, 3)
      }.disabled(isSlide)

      Button(action: {
        isSlide.toggle()
        switchHandler(isSlide)
      }) {
        Image("icStack")
          .resizable()
          .padding(13)
          .foregroundColor(.black)
          .frame(width: 50, height: 50)
          .background(
            RoundedRectangle(cornerRadius: 10)
              .foregroundColor(!isSlide ? .init(.systemGray6) : .white)
              .shadow(radius: 5)
          )
          .padding(.leading, 3)
      }.disabled(!isSlide)

    }
  }
}
