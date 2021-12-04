//
//  KotlinCoreAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 27/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import KotlinCore

protocol KotlinCoreAssembler {

  // domain
  func resolve() -> GetMangaListUseCase
  func resolve() -> GetMangaSearchUseCase
  func resolve() -> GetMangaTrendingUseCase
  func resolve() -> GetMangaDetailUseCase
  func resolve() -> GetMangaFavoriteUseCase

  // data
  func resolve() -> MangaRepository
  func resolve() -> RemoteDataSource
  func resolve() -> LocalDataSource
}

extension KotlinCoreAssembler where Self: Assembler {

  // MARK: domain
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

  func resolve() -> GetMangaFavoriteUseCase {
    return GetMangaFavoriteInteractor(repository: resolve())
  }


  // MARK: data
  func resolve() -> MangaRepository {
    return DefaultMangaRepository(remoteDataSource: resolve(), localDataSource: resolve())
  }

  func resolve() -> RemoteDataSource {
    let json = CoreKt.createJson()
    return RemoteDataSource(ktor: CoreKt.createKtorClient(json: json))
  }

  func resolve() -> LocalDataSource {
    return LocalDataSource(realm: CoreKt.createRealmDatabase())
  }
}
