//
//  ContentView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore

struct ContentView: View {

  @State var selectedIndex = 0

  var body: some View {
    ZStack {
      if selectedIndex == 0 {
        Text("Saved")
      } else if selectedIndex == 1 {
        Text("Browse")
      } else if selectedIndex == 2 {
        Text("My Mangas")
      }

      VStack {
        Spacer()
        tabView.padding(.bottom, 20)
      }
    }
  }

  var tabView: some View {
    HStack {
      Button(action: {
        selectedIndex = 0
      }, label: {
        VStack {
          Image(systemName: "rectangle.3.offgrid")
            .resizable()
            .foregroundColor(.green)
            .frame(width: 25, height: 25, alignment: .center)
        }
      }).padding(.leading, 40)

      Button(action: {
        selectedIndex = 1
      }, label: {
        VStack {
          Image(systemName: "rectangle.stack")
            .resizable()
            .foregroundColor(.yellow)
            .frame(width: 25, height: 25, alignment: .center)
        }
      }).padding(.leading, 40)

      Button(action: {
        selectedIndex = 2
      }, label: {
        VStack {
          Image(systemName: "person")
            .resizable()
            .foregroundColor(.purple)
            .frame(width: 25, height: 25, alignment: .center)
        }
      }).padding(.horizontal, 40)
    }
    .frame(maxWidth: .infinity, minHeight: 80)
    .background(
      Color.white
        .cornerRadius(12)
        .shadow(radius: 10)
    )
    .padding(.horizontal, 70)
  }
}

struct ContentView_Previews: PreviewProvider {
  static var previews: some View {
    ContentView()
  }
}
