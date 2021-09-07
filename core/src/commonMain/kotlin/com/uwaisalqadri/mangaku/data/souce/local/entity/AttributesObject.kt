package com.uwaisalqadri.mangaku.data.souce.local.entity

import io.realm.RealmObject

class AttributesObject: RealmObject {
    var ageRating: String = ""
    var ageRatingGuide: String = ""
    var averageRating: String = ""
    var canonicalTitle: String = ""
    var chapterCount: String = ""
    var coverImage: CoverImageObject? = null
    var createdAt: String = ""
    var description: String = ""
    var endDate: String = ""
    var favoritesCount: String = ""
    var mangaType: String = ""
    var nextRelease: String = ""
    var popularityRank: String = ""
    var posterImage: PosterImageObject? = null
    var ratingRank: String = ""
    var serialization: String = ""
    var slug: String = ""
    var startDate: String = ""
    var status: String = ""
    var subtype: String = ""
    var synopsis: String = ""
    var tba: String = ""
    var titles: TitlesObject? = null
    var updatedAt: String = ""
    var userCount: String = ""
    var volumeCount: String = ""
}