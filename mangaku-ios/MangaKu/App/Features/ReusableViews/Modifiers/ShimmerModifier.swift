//
//  ShimmerModifier.swift
//  iosApp
//
//  Created by Uwais Alqadri on 5/9/22.
//  Copyright © 2022 Uwais Alqadri. All rights reserved.
//

import SwiftUI

struct Shimmer: ViewModifier {

  @State private var phase: CGFloat = 0

  var duration = 1.5
  var bounce = false

  func body(content: Content) -> some View {
    content
      .modifier(
        AnimatedMask(phase: phase)
          .animation(
            Animation
              .linear(duration: duration)
              .repeatForever(autoreverses: bounce)
          )
      )
      .onAppear { phase = 0.8 }
  }

  /// An animatable modifier to interpolate between `phase` values.
  struct AnimatedMask: AnimatableModifier {
    var phase: CGFloat = 0

    var animatableData: CGFloat {
      get { phase }
      set { phase = newValue }
    }

    func body(content: Content) -> some View {
      content
        .mask(GradientMask(phase: phase).scaleEffect(3))
    }
  }

  /// A slanted, animatable gradient between transparent and opaque to use as mask.
  /// The `phase` parameter shifts the gradient, moving the opaque band.
  struct GradientMask: View {
    let phase: CGFloat
    let centerColor: Color = .black
    let edgeColor: Color = .black.opacity(0.3)

    var body: some View {
      LinearGradient(gradient: Gradient(
        stops: [
          .init(color: edgeColor, location: phase),
          .init(color: centerColor, location: phase + 0.1),
          .init(color: edgeColor, location: phase + 0.2)
        ]
      ), startPoint: .topLeading, endPoint: .bottomTrailing)
    }
  }
}

extension View {
  /// Adds an animated shimmering effect to any view, typically to show that
  /// an operation is in progress. use .redacted(...) modifier to create interesting animated skeleton views
  /// - Parameters:
  ///   - duration: The duration of a shimmer cycle in seconds. Default: `1.5`.
  ///   - bounce: Whether to bounce (reverse) the animation back and forth. Defaults to `false`.
  @ViewBuilder func shimmering(isActive: Bool = true, duration: Double = 1.5, bounce: Bool = false) -> some View {
    if isActive {
      modifier(Shimmer(duration: duration, bounce: bounce))
    } else {
      self
    }
  }
}
