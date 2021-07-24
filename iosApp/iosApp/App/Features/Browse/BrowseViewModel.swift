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
  @Published var mangas = [Manga]()

  private var cancellables: Set<AnyCancellable> = []
  private let listUseCase: GetMangaListUseCase

  init(listUseCase: GetMangaListUseCase) {
    self.listUseCase = listUseCase
  }

  func fetchMangas() {
    createPublisher(for: listUseCase.executeNative())
      .receive(on: RunLoop.main)
      .sink(receiveCompletion: { completion in
        print("Received completion: \(completion)")
      }, receiveValue: { value in
        print("Received value: \(value)")
      }).store(in: &cancellables)
  }
}
