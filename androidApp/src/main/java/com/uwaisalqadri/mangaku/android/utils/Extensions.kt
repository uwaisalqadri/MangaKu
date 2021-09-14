package com.uwaisalqadri.mangaku.android.utils

import com.uwaisalqadri.mangaku.domain.model.Manga

fun Manga.getTitle(): String {
    val title = attributes?.titles
    return title?.ja_jp ?: title?.en_jp ?: title?.en_us ?: title?.en ?: ""
}