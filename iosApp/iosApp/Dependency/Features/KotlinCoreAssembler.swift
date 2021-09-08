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

  // usecase
  func resolve() -> GetMangaListUseCase
  func resolve() -> GetMangaSearchUseCase
  func resolve() -> GetMangaTrendingUseCase
  func resolve() -> GetMangaDetailUseCase
  func resolve() -> GetMangaFavoriteUseCase
  func resolve() -> CreateMangaFavoriteUseCase

  // data source
  func resolve() -> MangaRepository
  func resolve() -> RemoteDataSource
  func resolve() -> LocalDataSource

  // mapper
  func resolve() -> MangaMapper
  func resolve() -> MangaObjectMapper
  func resolve() -> AttributesMapper
  func resolve() -> AttributesObjectMapper
  func resolve() -> PosterImageMapper
  func resolve() -> PosterImageObjectMapper
  func resolve() -> CoverImageMapper
  func resolve() -> CoverImageObjectMapper
  func resolve() -> TitlesMapper
  func resolve() -> TitlesObjectMapper
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

  func resolve() -> GetMangaFavoriteUseCase {
    return GetMangaFavoriteInteractor(repository: resolve())
  }

  func resolve() -> CreateMangaFavoriteUseCase {
    return CreateMangaFavoriteInteractor(repository: resolve())
  }

  func resolve() -> MangaRepository {
    return MangaRepositoryImpl(remoteDataSource: resolve(), localDataSource: resolve(), mangaResponseMapper: resolve(), mangaObjectMapper: resolve())
  }

  func resolve() -> MangaMapper {
    return MangaMapper(attributesMapper: resolve())
  }

  func resolve() -> MangaObjectMapper {
    return MangaObjectMapper(attributesObjectMapper: resolve())
  }

  func resolve() -> AttributesMapper {
    return AttributesMapper(coverImageMapper: resolve(), posterImageMapper: resolve(), titlesMapper: resolve())
  }

  func resolve() -> AttributesObjectMapper {
    return AttributesObjectMapper(coverImageMapper: resolve(), posterImageMapper: resolve(), titlesMapper: resolve())
  }

  func resolve() -> CoverImageMapper {
    return CoverImageMapper()
  }

  func resolve() -> CoverImageObjectMapper {
    return CoverImageObjectMapper()
  }

  func resolve() -> PosterImageMapper {
    return PosterImageMapper()
  }

  func resolve() -> PosterImageObjectMapper {
    return PosterImageObjectMapper()
  }

  func resolve() -> TitlesMapper {
    return TitlesMapper()
  }

  func resolve() -> TitlesObjectMapper {
    return TitlesObjectMapper()
  }

  func resolve() -> RemoteDataSource {
    let baseUrl = Constants.init().BASE_URL
    let json = CoreKt.createJson()
    return RemoteDataSource(client: CoreKt.createHttpClient(json: json), baseUrl: baseUrl)
  }

  func resolve() -> LocalDataSource {
    return LocalDataSource(realm: CoreKt.createRealmDatabase())
  }
}
