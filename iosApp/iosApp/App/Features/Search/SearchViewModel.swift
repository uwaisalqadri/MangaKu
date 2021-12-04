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
  @Published var loading = false
  @Published var searchQuery: String = ""

  private let searcUseCase: GetMangaSearchUseCase
  private var cancellables = Set<AnyCancellable>()

  init(searcUseCase: GetMangaSearchUseCase) {
    self.searcUseCase = searcUseCase

    $searchQuery
      .debounce(for: .milliseconds(800), scheduler: RunLoop.main)
      .removeDuplicates()
      .map { string in
        if string.count < 1 {
          self.mangas = []
          return nil
        }
        return string
      }
      .compactMap { $0 }
      .sink { (_) in

      } receiveValue: { searchField in
        self.fetchSearchManga(query: searchField)

      }.store(in: &cancellables)
  }

  private func fetchSearchManga(query: String) {
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
