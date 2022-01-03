//
//  DetailViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import KotlinCore
import KMPNativeCoroutinesCombine
import Combine

class DetailViewModel: ObservableObject {

  @Published var manga: ViewState<Manga> = .initiate

  private let detailUseCase: DetailUseCase
  private var cancellables = Set<AnyCancellable>()

  init(detailUseCase: DetailUseCase) {
    self.detailUseCase = detailUseCase
  }

  func fetchManga(mangaId: String) {
    manga = .loading
    createPublisher(for: detailUseCase.getDetailMangaNative(mangaId: mangaId))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished: ()
        case .failure(let error):
          self.manga = .error(error: error)
        }
      } receiveValue: { value in
        if let manga = value, value != nil {
          self.manga = .success(data: manga)
        } else {
          self.manga = .empty
        }
      }.store(in: &cancellables)
  }

}
