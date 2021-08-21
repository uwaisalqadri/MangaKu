//
//  KotlinCoreAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 27/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import KotlinCore

protocol KotlinCoreAssembler {
  func resolve() -> GetMangaListUseCase
  func resolve() -> GetMangaSearchUseCase
  func resolve() -> GetMangaTrendingUseCase
  func resolve() -> GetMangaDetailUseCase
  func resolve() -> MangaRepository
  func resolve() -> RemoteDataSource
}

extension KotlinCoreAssembler where Self: Assembler {

  func resolve() -> GetMangaListUseCase {
    return GetMangaListInteractor(repository: resolve())
  }

  func resolve() -> GetMangaSearchUseCase {
    return GetMangaSearchInteractor(repository: resolve())
  }

  func resolve() -> GetMangaTrendingUseCase {
    return GetMangaTrendingInteractor(repository: resolve())
  }

  func resolve() -> GetMangaDetailUseCase {
    return GetMangaDetailInteractor(repository: resolve())
  }

  func resolve() -> MangaRepository {
    return MangaRepositoryImpl(remoteDataSource: resolve())
  }

  func resolve() -> RemoteDataSource {
    let baseUrl = Constants.init().BASE_URL
    let json = CoreKt.createJson()
    return RemoteDataSource(client: CoreKt.createHttpClient(json: json), baseUrl: baseUrl)
  }
}
