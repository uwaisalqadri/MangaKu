//
//  MyMangaView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct MyMangaView: View {
  @StateObject var viewModel: MyMangaViewModel
  @State private var isSlide = true
  
  init() {
    _viewModel = StateObject(wrappedValue: MyMangaViewModel())
  }
  
  var body: some View {
    ScrollView(showsIndicators: false) {
      VStack {
        Text("My Manga")
          .font(.custom(.mbold, size: 23))
          .padding(.top, 30)
        
        LayoutSwitch() { toggle in
          isSlide = toggle
        }.padding(.bottom, 15)
        
        if viewModel.state.items.isEmpty {
          Text("Still Empty Here!")
            .foregroundColor(.black)
            .font(.custom(.sedgwickave, size: 60))
            .multilineTextAlignment(.center)
            .padding(.top, 50)
            .padding(.horizontal, 20)
          
        } else {
          if isSlide {
            MangaCarouselView(
              itemWidth: 240,
              itemHeight: 361,
              views: getMangaItemView(items: viewModel.state.items)
            ).frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight / 2)
              .padding(.top, 50)
            
          } else {
            LazyVGrid(columns: [
              GridItem(.adaptive(minimum: 120), spacing: 25, alignment: .center)
            ], alignment: .leading, spacing: 10) {
              
              ForEach(viewModel.state.items, id: \.self) { manga in
                MangaGridItem(manga: manga)
              }
            }
            .padding(.horizontal, 30)
            .padding(.top, 20)
            .padding(.bottom, 200)
          }
        }
        
        Spacer()
      }
    }
    .navigationBarHidden(true)
    .onAppear {
      viewModel.send(action: .getMyMangas)
    }
    .frame(width: UIScreen.screenWidth, alignment: .center)
  }
  
  private func getMangaItemView(items: [Manga]) -> [AnyView] {
    var anyViews = [AnyView]()
    
    items.forEach { manga in
      anyViews.append(AnyView(
        WebImage(url: URL(string: manga.getPosterImage()))
          .resizable()
          .indicator(.activity)
          .cornerRadius(12)
          .overlay(
            MyMangaContentView(manga: manga)
          )
      ))
    }
    
    return anyViews
  }
}

struct MyMangaContentView: View {
  
  var manga: Manga
  
  var body: some View {
    ZStack(alignment: .bottomLeading) {
      
      LinearGradient(gradient: Gradient(colors: [.clear, .black]), startPoint: .top, endPoint: .bottom)
        .cornerRadius(12)
      
      VStack(alignment: .leading) {
        
        Text(manga.getTitle())
          .foregroundColor(.white)
          .font(.custom(.sedgwickave, size: 35))
          .multilineTextAlignment(.leading)
          .lineLimit(2)
          .padding(.bottom, 12)
        
        Text("Volume \(manga.attributes?.volumeCount ?? 0)")
          .foregroundColor(.white)
          .font(.custom(.mbold, size: 17))
          .padding(.bottom, 5)
        
        StarsView(manga: manga)
        
      }.padding(.horizontal, 17)
        .padding(.bottom, 35)
    }
  }
}
