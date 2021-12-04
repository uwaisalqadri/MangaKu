//
//  LottieView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 17/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import Lottie

struct LottieView: UIViewRepresentable {
  var name = "success"
  var loopMode: LottieLoopMode = .loop

  func makeUIView(context: Context) -> some UIView {
    let view = UIView(frame: .zero)

    let animationView = AnimationView()
    let animation = Animation.named(name)
    animationView.animation = animation
    animationView.contentMode = .scaleAspectFill
    animationView.loopMode = loopMode
    animationView.play()

    animationView.translatesAutoresizingMaskIntoConstraints = false
    view.addSubview(animationView)
    NSLayoutConstraint.activate([
      animationView.heightAnchor.constraint(equalTo: view.heightAnchor),
      animationView.widthAnchor.constraint(equalTo: view.widthAnchor)
    ])

    return view
  }

  func updateUIView(_ uiView: UIViewType, context: Context) {

  }

}
