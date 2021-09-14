//
//  DetailViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore
import KMPNativeCoroutinesCombine
import Combine

class DetailViewModel: ObservableObject {

  //  @Published var mangas = Manga(
  @Published var loading = false
  @Published var errorMessage = ""

  private let detailUseCase: GetMangaDetailUseCase
  private var cancellables = Set<AnyCancellable>()

  init(detailUseCase: GetMangaDetailUseCase) {
    self.detailUseCase = detailUseCase
  }

  func fetchManga(mangaId: String) {
    loading = true
    createPublisher(for: detailUseCase.invokeNative(id: mangaId))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        guard let detail = value else { return }
        print("DETAIL", detail)
      }.store(in: &cancellables)
  }

}
