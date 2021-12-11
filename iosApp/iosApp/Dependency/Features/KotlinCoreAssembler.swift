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
  func resolve() -> BrowseUseCase
  func resolve() -> SearchUseCase
  func resolve() -> DetailUseCase
  func resolve() -> MyMangaUseCase

  // data
  func resolve() -> MangaRepository
  func resolve() -> MangaRemoteDataSource
  func resolve() -> MangaLocalDataSource
}

extension KotlinCoreAssembler where Self: Assembler {

  // MARK: domain
  func resolve() -> BrowseUseCase {
    return BrowseInteractor(repository: resolve())
  }

  func resolve() -> SearchUseCase {
    return SearchInteractor(repository: resolve())
  }

  func resolve() -> DetailUseCase {
    return DetailInteractor(repository: resolve())
  }

  func resolve() -> MyMangaUseCase {
    return MyMangaInteractor(repository: resolve())
  }


  // MARK: data
  func resolve() -> MangaRepository {
    return DefaultMangaRepository(mangaRemoteDataSource: resolve(), mangaLocalDataSource: resolve())
  }

  func resolve() -> MangaRemoteDataSource {
    return DefaultMangaRemoteDataSource(ktor: CoreModuleKt.createKtorClient(json: CoreModuleKt.createJson()))
  }

  func resolve() -> MangaLocalDataSource {
    return DefaultMangaLocalDataSource(realm: CoreModuleKt.createRealmDatabase())
  }
}
