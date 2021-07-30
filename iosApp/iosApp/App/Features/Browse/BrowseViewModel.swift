//
//  BrowseViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore
import Combine
import KMPNativeCoroutinesCombine

class BrowseViewModel: ObservableObject {

  @Published var loading = false
  @Published var mangas: [Manga] = []

  private let listUseCase: GetMangaListUseCase
  private var cancellables = Set<AnyCancellable>()

  init(listUseCase: GetMangaListUseCase) {
    self.listUseCase = listUseCase
  }

  func fetchMangas() {
    // create publisher for kotlin flow
    createPublisher(for: listUseCase.executeNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
        print("receive completion \(completion)")
      } receiveValue: { value in
        self.mangas = value
        print(self.mangas)
      }.store(in: &cancellables)
  }
}
