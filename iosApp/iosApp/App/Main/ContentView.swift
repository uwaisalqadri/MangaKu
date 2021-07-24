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

  @State var selectedIndex = 1

  var body: some View {
    ZStack {
      if selectedIndex == 0 {
        SavedView()
      } else if selectedIndex == 1 {
        BrowseView()
      } else if selectedIndex == 2 {
        MyMangaView()
      }

      VStack {
        Spacer()
        tabView.padding(.bottom, 30)
      }
    }
  }

  var tabView: some View {
    HStack {
      Button(action: {
        selectedIndex = 0
      }, label: {
        VStack {
          Image("icSaved")
            .resizable()
            .frame(width: 25, height: 30, alignment: .center)
        }
      }).padding(.horizontal, 30)

      Button(action: {
        selectedIndex = 1
      }, label: {
        VStack {
          Image("icBrowse")
            .resizable()
            .frame(width: 25, height: 25, alignment: .center)
        }
      }).padding(.horizontal, 20)

      Button(action: {
        selectedIndex = 2
      }, label: {
        VStack {
          Image("icMyMangas")
            .resizable()
            .frame(width: 25, height: 30, alignment: .center)
        }
      }).padding(.horizontal, 30)
    }
    .frame(maxWidth: .infinity, minHeight: 80)
    .background(
      Color.white
        .cornerRadius(12)
        .shadow(radius: 20)
    )
    .padding(.horizontal, 40)
  }
}
