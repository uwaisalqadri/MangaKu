//
//  SavedViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import KotlinCore
import Combine
import KMPNativeCoroutinesCombine

class SearchViewModel: ObservableObject {

  @Published var mangas = [Manga]()
  @Published var errorMessage = ""
  @Published var isLoading = false

  private let searcUseCase: SearchUseCase
  private var cancellables = Set<AnyCancellable>()

  init(searcUseCase: SearchUseCase) {
    self.searcUseCase = searcUseCase
  }

  func fetchSearchManga(query: String) {
    isLoading = true
    createPublisher(for: searcUseCase.getSearchMangaNative(query: query))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.isLoading = false
        case .failure(let error):
          guard let apiError = error.apiError else { return }
          self.errorMessage = apiError.errorMessage
        }
      } receiveValue: { value in
        self.mangas = value
      }.store(in: &cancellables)
  }
}
