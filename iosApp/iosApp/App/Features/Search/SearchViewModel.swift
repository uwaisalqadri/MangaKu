//
//  SavedViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore
import Combine
import KMPNativeCoroutinesCombine

class SearchViewModel: ObservableObject {

  @Published var mangas = [Manga]()
  @Published var errorMessage = ""
  @Published var loading = false

  private let searcUseCase: GetMangaSearchUseCase
  private var cancellables = Set<AnyCancellable>()

  init(searcUseCase: GetMangaSearchUseCase) {
    self.searcUseCase = searcUseCase
  }

  func fetchSearchManga(query: String) {
    loading = true
    createPublisher(for: searcUseCase.invokeNative(query: query))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        self.mangas = value
      }.store(in: &cancellables)
  }
}
