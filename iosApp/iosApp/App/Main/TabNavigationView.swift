//
//  TabNavigationView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import KotlinCore

struct TabNavigationView: View {

  @State var selectedIndex = 0
  let assembler: Assembler

  var body: some View {
    NavigationView {
      ZStack {
        switch selectedIndex {
        case 1:
          let navigator: MyMangaRouter = assembler.resolve()
          navigator.routeToMyManga()
        default:
          let navigator: BrowseRouter = assembler.resolve()
          navigator.routeToBrowse()
            .animation(.none, value: selectedIndex)
        }

        VStack {
          Spacer()
          tabView.padding(.bottom, 30)
        }
      }
    }.accentColor(.black)

  }
}

extension TabNavigationView {

  var tabView: some View {
    HStack {
      Button(action: {
        selectedIndex = 0
      }, label: {
        VStack {
          Image(selectedIndex != 0 ? "icBrowseUn" : "icBrowse")
            .resizable()
            .frame(width: 25, height: 25, alignment: .center)
        }
      }).padding(.horizontal, 35)

      Button(action: {
        selectedIndex = 1
      }, label: {
        VStack {
          Image(selectedIndex != 1 ? "icSavedUn" : "icSaved")
            .resizable()
            .frame(width: 25, height: 30, alignment: .center)
        }
      }).padding(.horizontal, 35)

    }.frame(maxWidth: 230, minHeight: 80)
    .background(
      Color.white
        .cornerRadius(12)
        .shadow(radius: 20)
    )
    .padding(.horizontal, 40)
  }
}
