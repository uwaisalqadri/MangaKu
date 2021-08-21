//
//  SavedView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SavedView: View {
  var body: some View {
    VStack {
      List(0..<10) { _ in
        ShimmerItemView()
      }
    }.padding(30)
  }
}

struct SavedView_Previews: PreviewProvider {
  static var previews: some View {
    SavedView()
  }
}
