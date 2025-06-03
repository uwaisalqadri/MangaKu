//
//  TabNavigationView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared

enum Tab: Int {
  case browse
  case saved
  
  var iconName: String {
    switch self {
    case .browse: return "icBrowse"
    case .saved: return "icSaved"
    }
  }
  
  var unselectedIconName: String {
    switch self {
    case .browse: return "icBrowseUn"
    case .saved: return "icSavedUn"
    }
  }
}

struct TabNavigationView: View {
  @State private var selectedTab: Tab = .browse
  let assembler: Assembler
  
  var body: some View {
    NavigationView {
      ZStack {
        switch selectedTab {
        case .browse:
          BrowseView()
            .animation(.none, value: selectedTab)
        case .saved:
          MyMangaView()
        }
        
        VStack {
          Spacer()
          tabView.padding(.bottom, 30)
        }
      }
    }
    .accentColor(.black)
  }
}

extension TabNavigationView {
  var tabView: some View {
    HStack {
      ForEach([Tab.browse, Tab.saved], id: \.self) { tab in
        Button(action: {
          selectedTab = tab
        }) {
          VStack {
            Image(selectedTab == tab ? tab.iconName : tab.unselectedIconName)
              .resizable()
              .frame(width: 25, height: tab == .saved ? 30 : 25)
          }
        }
        .padding(.horizontal, 35)
      }
    }
    .frame(maxWidth: 230, minHeight: 80)
    .background(
      Color.white
        .cornerRadius(12)
        .shadow(radius: 20)
    )
    .padding(.horizontal, 40)
  }
}
