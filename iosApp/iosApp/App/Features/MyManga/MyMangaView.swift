//
//  MyMangaView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore
import SDWebImageSwiftUI
import ACarousel

struct MyMangaView: View {

  @ObservedObject var viewModel: MyMangaViewModel
  @State var position = 0

  var body: some View {
    NavigationView {
      VStack {
        Text("My Manga")
          .font(.custom(.mbold, size: 23))
          .padding(.top, -70)
          .padding(.bottom, 30)

        if !viewModel.loading, !viewModel.mangas.isEmpty {
          ZStack(alignment: Alignment(horizontal: .center, vertical: .top)) {
            Text(viewModel.mangas[position].getTitle())
              .font(.custom(.sedgwickave, size: 70))
              .lineLimit(2)
              .multilineTextAlignment(.center)
              .padding(.top, -60)

            ACarousel(
              viewModel.mangas,
              id: \.self,
              spacing: 20,
              headspace: 50,
              isWrap: true) { manga in

              WebImage(url: URL(string: manga.attributes?.posterImage?.original ?? ""))
                .resizable()
                .indicator(.activity)
                .cornerRadius(12)

            }.frame(height: 400)
            .padding(.top, 70)
//            .simultaneousGesture(
//              DragGesture().onEnded(onDragEnded)
//            )
          }
        }

        Spacer()
      }
    }
  }

  private func onDragEnded(drag: DragGesture.Value) {
    let dragThreshold: CGFloat = 200
    if drag.predictedEndTranslation.width > dragThreshold || drag.translation.width > dragThreshold {

      if position != viewModel.mangas.startIndex {
        position = position - 1
      } else {
        position = viewModel.mangas.count - 1
      }

    } else if (drag.predictedEndTranslation.width) < (-1 * dragThreshold) || (drag.translation.width) < (-1 * dragThreshold) {

      if position != viewModel.mangas.count - 1 {
        position = position + 1
      } else {
        position = 0
      }

    }
  }
}
