//
//  BrowseAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore

protocol BrowseAssembler {
  func resolve() -> BrowseViewModel
  func resolve() -> GetMangaListUseCase
  func resolve() -> MangaRepository
  func resolve() -> ApiService
}

extension BrowseAssembler where Self: Assembler {

  func resolve() -> BrowseViewModel {
    return BrowseViewModel(listUseCase: resolve())
  }

  func resolve() -> GetMangaListUseCase {
    return GetMangaListInteractor(repository: resolve())
  }

  func resolve() -> ApiService {
    let baseUrl = Constants.init().BASE_URL
    let json = CoreKt.createJson()
    return ApiService(client: CoreKt.createHttpClient(json: json), baseUrl: baseUrl)
  }

  func resolve() -> MangaRepository {
    return MangaRepositoryImpl(apiService: resolve())
  }
}
