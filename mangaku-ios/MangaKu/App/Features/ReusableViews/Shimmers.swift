//
//  Shimmers.swift
//  iosApp
//
//  Created by Uwais Alqadri on 21/08/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct ShimmerBrowseView: View {
  var body: some View {
    HStack {
      Color(.systemGray3)
        .frame(width: 124, height: 200)
        .cornerRadius(12)
        .shimmering()
      
      VStack(alignment: .leading) {
        
        Color(.systemGray3)
          .frame(height: 30)
          .cornerRadius(12)
          .shimmering()
          .padding(.top, 5)
        
        HStack {
          Color(.systemGray3)
            .frame(width: 80, height: 20)
            .cornerRadius(12)
            .shimmering()
          
          Color(.systemGray3)
            .frame(width: 60, height: 20)
            .cornerRadius(12)
            .shimmering()
          
        }.padding(.top, 5)
        
        Spacer(minLength: 30)
        
        Color(.systemGray3)
          .frame(width: 100, height: 40)
          .cornerRadius(9)
          .shimmering()
          .padding(.bottom, 10)
        
        
      }.padding(.leading, 15)
      
      Spacer()
      
    }.padding(.bottom, 30)
  }
}


struct ShimmerSearchView: View {
  
  var body: some View {
    Color(.systemGray3)
      .frame(height: 140)
      .cornerRadius(8)
      .shimmering()
      .padding(.bottom, 20)
  }
}

struct ShimmerDetailView: View {
  
  var body: some View {
    VStack(alignment: .leading) {
      Color(.systemGray3)
        .frame(height: 200)
        .cornerRadius(10)
        .shimmering()
        .padding(.horizontal, 24)
      
      VStack(alignment: .leading) {
        Color(.systemGray3)
          .frame(height: 40)
          .cornerRadius(8)
          .shimmering()
          .padding(.leading, 24)
          .padding(.trailing, 80)
        
        Color(.systemGray3)
          .frame(height: 25)
          .cornerRadius(8)
          .shimmering()
          .padding(.leading, 24)
          .padding(.trailing, 50)
        
        HStack {
          
          Color(.systemGray3)
            .frame(width: 100, height: 30)
            .cornerRadius(8)
            .shimmering()
            .padding(.leading, 24)
          
          HStack {
            Image(systemName: "star.fill")
              .foregroundColor(.yellow)
              .frame(width: 15, height: 15)
            
            Color(.systemGray3)
              .frame(width: 100, height: 20)
              .cornerRadius(8)
              .shimmering()
              .padding(.leading, 7)
            
          }.padding(.leading, 5)
          
        }.padding(.top, 10)
        
        Color(.systemGray3)
          .frame(height: 40)
          .cornerRadius(8)
          .shimmering()
          .padding(.leading, 24)
          .padding(.trailing, 100)
          .padding(.top, 50)
        
        ForEach(0..<10) { _ in
          
          Color(.systemGray3)
            .frame(height: 20)
            .cornerRadius(8)
            .shimmering()
            .padding(.vertical, -15)
          
        }.padding(.horizontal, 24)
          .padding(.top, 30)
        
        Spacer(minLength: 400)
      }
    }
  }
}










