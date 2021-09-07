package com.uwaisalqadri.mangaapp.domain.model

import com.uwaisalqadri.mangaapp.data.souce.remote.response.AttributesItem

data class Manga(
    val attributes: Attributes,
    val id: String,
    val type: String
)