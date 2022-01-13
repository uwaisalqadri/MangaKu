//
//  ShimmerView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 21/08/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct ShimmerView: View {

  private struct Constants {
    static let duration: Double = 0.9
    static let minOpacity: Double = 0.25
    static let maxOpacity: Double = 1.0
    static let cornerRadius: CGFloat = 2.0
  }

  @State private var opacity: Double = Constants.minOpacity
  var isAnimating: Bool = true

  var body: some View {
    RoundedRectangle(cornerRadius: Constants.cornerRadius)
      .fill(Color.init(.systemGray5))
      .opacity(opacity)
      .transition(.opacity)
      .drawingGroup()
      .onAppear {
        let baseAnimation = Animation.easeInOut(duration: Constants.duration)
        let repeated = baseAnimation.repeatForever(autoreverses: true)
        if isAnimating {
          withAnimation(repeated) {
            self.opacity = Constants.maxOpacity
          }
        }
      }
  }
}

struct ShimmerView_Previews: PreviewProvider {
  static var previews: some View {
    ShimmerView()
  }
}
