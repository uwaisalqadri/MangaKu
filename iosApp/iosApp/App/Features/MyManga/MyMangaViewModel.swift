//
//  MyMangaViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import KotlinCore
import KMPNativeCoroutinesCombine

class MyMangaViewModel: ObservableObject {

  @Published var mangas = [Manga]()
  @Published var errorMessage = ""
  @Published var loading = false

  private let favoriteUseCase: GetMangaFavoriteUseCase
  private var cancellables = Set<AnyCancellable>()

  init(favoriteUseCase: GetMangaFavoriteUseCase) {
    self.favoriteUseCase = favoriteUseCase
    fetchFavoriteManga()
  }

  private func fetchFavoriteManga() {
    loading = true
    createPublisher(for: favoriteUseCase.invokeNative())
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
