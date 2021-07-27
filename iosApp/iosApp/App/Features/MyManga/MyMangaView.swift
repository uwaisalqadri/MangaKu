//
//  MyMangaView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct MyMangaView: View {
  var body: some View {
    ScrollView(.horizontal, showsIndicators: false) {
      HStack {
        ForEach(0..<5) { _ in
          MyMangaCardView()
        }
      }.padding(.leading, 50)
    }
  }
}

struct MyMangaView_Previews: PreviewProvider {
  static var previews: some View {
    MyMangaView()
  }
}
