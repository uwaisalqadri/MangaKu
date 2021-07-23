package com.uwaisalqadri.mangaapp.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Relationships(
    val castings: Castings,
    val categories: Categories,
    val chapters: Chapters,
    val characters: Characters,
    val genres: Genres,
    val installments: Installments,
    val mangaCharacters: MangaCharacters,
    val mangaStaff: MangaStaff,
    val productions: Productions,
    val quotes: Quotes
)