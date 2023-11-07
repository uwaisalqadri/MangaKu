package com.uwaisalqadri.mangaku.data.souce.local.entity

import io.realm.kotlin.types.RealmObject

class AttributesObject: RealmObject {
    var ageRating: String = ""
    var ageRatingGuide: String = ""
    var averageRating: Double = 0.0
    var canonicalTitle: String = ""
    var chapterCount: Int = 0
    var coverImage: CoverImageObject? = null
    var description: String = ""
    var endDate: String = ""
    var favoritesCount: Int = 0
    var mangaType: String = ""
    var nextRelease: String = ""
    var popularityRank: String = ""
    var posterImage: PosterImageObject? = null
    var ratingRank: Int = 0
    var serialization: String = ""
    var slug: String = ""
    var startDate: String = ""
    var status: String = ""
    var subtype: String = ""
    var synopsis: String = ""
    var tba: String = ""
    var titles: TitlesObject? = null
    var userCount: Int = 0
    var volumeCount: Int = 0
}