//
//  DetailView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct DetailView: View {
  @StateObject var viewModel: DetailViewModel
  @StateObject var mangaViewModel: MyMangaViewModel
  @State private var isShowDialog = false
    
  init(mangaId: String) {
    _viewModel = StateObject(wrappedValue: DetailViewModel(mangaId: mangaId))
    _mangaViewModel = StateObject(wrappedValue: MyMangaViewModel())
  }
  
  var body: some View {
    VStack(alignment: .leading) {
      
      ScrollView(showsIndicators: false) {
        switch true {
        case viewModel.state.isLoading:
          ShimmerDetailView()
        default:
          if let data = viewModel.state.manga {
            WebImage(url: URL(string: data.coverImage.url))
              .resizable()
              .indicator(.activity)
              .clipped()
              .frame(height: 200)
              .cornerRadius(10)
              .padding(.horizontal, 24)
              .onAppear {
                mangaViewModel.send(action: .checkFavorite(mangaId: data.id))
              }
            
            VStack(alignment: .leading) {
              Text(data.title)
                .foregroundColor(.black)
                .font(.custom(.mbold, size: 25))
                .padding(.top, 15)
              
              Text(data.slug)
                .foregroundColor(.black)
                .font(.custom(.mmedium, size: 15))
              
              HStack {
                Text(DisplayDate(raw: data.startDate).string() ?? "-")
                  .foregroundColor(.white)
                  .font(.custom(.mbold, size: 13))
                  .padding(10)
                  .background(Color.black)
                  .cornerRadius(5)
                
                HStack {
                  Image(systemName: "star.fill")
                    .foregroundColor(.yellow)
                    .frame(width: 15, height: 15)
                  
                  Text(String(data.averageRating).removeCharacters(from: "0"))
                    .foregroundColor(.black)
                    .font(.custom(.msemibold, size: 14))
                  
                }.padding(.leading, 5)
                
              }.padding(.top, 10)
              
              Text("Description")
                .foregroundColor(.black)
                .font(.custom(.mbold, size: 21))
                .padding(.top, 50)
              
              Text(data.synopsis)
                .foregroundColor(.black)
                .font(.custom(.mmedium, size: 15))
                .padding(.top, 15)
              
              Spacer(minLength: 400)
              
            }.padding(.horizontal, 30)
          }
        }
      }.padding(.top, 30)
      
    }
    .navigationTitle("Detail")
    .navigationBarItems(trailing: Button(action: {
      if let data = viewModel.state.manga {
        mangaViewModel.send(
          action: mangaViewModel.state.isFavorite ?
            .deleteFavorite(mangaId: data.id) :
            .addFavorite(manga: data)
        )
        isShowDialog.toggle()
      }
    }) {
      Image(systemName: mangaViewModel.state.isFavorite ? "heart.fill" : "heart")
        .resizable()
        .foregroundColor(.red)
        .frame(width: 22, height: 20)
    })
    .onAppear {
      viewModel.send(action: .getDetail(mangaId: viewModel.state.mangaId))
    }
    .customDialog(isShowing: $isShowDialog) {
      VStack(alignment: .center) {
        Image(systemName: "heart.fill")
          .resizable()
          .foregroundColor(.red)
          .frame(width: 33, height: 30)
          .padding(.bottom, 15)
        
        Text(mangaViewModel.state.isFavorite ? "Added to Favorite" : "Removed from Favorite")
          .foregroundColor(.black)
          .font(.custom(.mbold, size: 17))
          .padding(.bottom, 10)
          .padding(.horizontal, 5)
          .multilineTextAlignment(.center)
        
      }.frame(width: 154, height: 154)
        .onAppear {
          if isShowDialog {
            DispatchQueue.main.asyncAfter(deadline: .now() + 1.6) {
              isShowDialog.toggle()
            }
          }
        }
    }
  }
}
