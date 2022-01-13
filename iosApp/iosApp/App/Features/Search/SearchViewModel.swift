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

  @Published var listManga: ViewState<[Manga]> = .initiate

  private let searcUseCase: SearchUseCase
  private var cancellables = Set<AnyCancellable>()

  init(searcUseCase: SearchUseCase) {
    self.searcUseCase = searcUseCase
  }

  func fetchSearchManga(query: String) {
    listManga = .loading
    createPublisher(for: searcUseCase.getSearchMangaNative(query: query))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished: ()
        case .failure(let error):
          self.listManga = .error(error: error)
        }
      } receiveValue: { value in
        self.listManga = value.isEmpty ? .empty : .success(data: value)
      }.store(in: &cancellables)
  }
}
