//
//  MyMangaViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore
import Combine
import KMPNativeCoroutinesCombine

class MyMangaViewModel: ObservableObject {

  @Published var mangas = [Manga]()
  @Published var loading = false
  @Published var errorMessage = ""

  private let listUseCase: GetMangaListUseCase
  private var cancellables = Set<AnyCancellable>()

  init(listUseCase: GetMangaListUseCase) {
    self.listUseCase = listUseCase
  }

  func fetchMangas() {
    self.loading = true
    createPublisher(for: listUseCase.invokeNative())
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
          print("my manga viewModel finished")
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        self.mangas = value
      }.store(in: &cancellables)
  }
}
