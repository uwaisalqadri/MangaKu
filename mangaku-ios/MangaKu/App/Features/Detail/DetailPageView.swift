//
//  DetailPageView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI
import Shared
import KMMViewModelSwiftUI

struct DetailPageView: View {
  @StateViewModel var viewModel: DetailViewModel
  @StateViewModel var mangaViewModel: MyMangaViewModel
  @State private var isShowDialog = false
  
  let mangaId: String
  
  private let appWillTerminate = NotificationCenter.default
    .publisher(for: UIApplication.willTerminateNotification)
  
  private var viewState: DetailState {
    viewModel.state
  }
  
  private var favState: MyMangaState {
    mangaViewModel.state
  }
  
  var body: some View {
    VStack(alignment: .leading) {
      
      ScrollView(showsIndicators: false) {
        switch true {
        case viewState.isLoading:
          ShimmerDetailView()
        default:
          if let data = viewState.manga {
            WebImage(url: URL(string: data.getCoverImage()))
              .resizable()
              .indicator(.activity)
              .clipped()
              .frame(height: 200)
              .cornerRadius(10)
              .padding(.horizontal, 24)
              .onAppear {
                mangaViewModel.onTriggerEvent(event: MyMangaEvent.CheckFavorite(mangaId: data.id))
              }
            
            VStack(alignment: .leading) {
              Text(data.getTitle())
                .foregroundColor(.black)
                .font(.custom(.mbold, size: 25))
                .padding(.top, 15)
              
              Text(data.attributes?.slug ?? "")
                .foregroundColor(.black)
                .font(.custom(.mmedium, size: 15))
              
              HStack {
                Text(DateFormatterKt.formatDate(dateString: data.attributes?.startDate ?? "", format: Shared.DateFormatter().CASUAL_DATE_FORMAT))
                  .foregroundColor(.white)
                  .font(.custom(.mbold, size: 13))
                  .padding(10)
                  .background(Color.black)
                  .cornerRadius(5)
                
                HStack {
                  Image(systemName: "star.fill")
                    .foregroundColor(.yellow)
                    .frame(width: 15, height: 15)
                  
                  Text(String(data.attributes?.averageRating ?? 0.0).removeCharacters(from: "0"))
                    .foregroundColor(.black)
                    .font(.custom(.msemibold, size: 14))
                  
                }.padding(.leading, 5)
                
              }.padding(.top, 10)
              
              Text("Description")
                .foregroundColor(.black)
                .font(.custom(.mbold, size: 21))
                .padding(.top, 50)
              
              Text(data.attributes?.synopsis ?? "")
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
      if let data = viewState.manga {
        favState.isFavorite ? mangaViewModel.onTriggerEvent(event: MyMangaEvent.DeleteFavorite(mangaId: data.id))
        : mangaViewModel.onTriggerEvent(event: MyMangaEvent.AddFavorite(manga: data))
        isShowDialog.toggle()
      }
    }) {
      Image(systemName: favState.isFavorite ? "heart.fill" : "heart")
        .resizable()
        .foregroundColor(.red)
        .frame(width: 22, height: 20)
    })
    .onAppear {
      viewModel.onTriggerEvent(event: DetailEvent.GetManga(id: mangaId))
    }
    .onReceive(appWillTerminate) { _ in
      mangaViewModel.closePersistence()
    }
    .customDialog(isShowing: $isShowDialog) {
      VStack(alignment: .center) {
        Image(systemName: "heart.fill")
          .resizable()
          .foregroundColor(.red)
          .frame(width: 33, height: 30)
          .padding(.bottom, 15)
        
        Text(favState.isFavorite ? "Added to Favorite" : "Removed from Favorite")
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
