package com.uwaisalqadri.mangaapp.data.souce.local.entity

import io.realm.RealmObject

class CoverImageObject: RealmObject {
    var large: String = ""
    var medium: String = ""
    var original: String = ""
    var small: String = ""
    var tiny: String = ""
}