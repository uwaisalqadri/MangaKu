package com.uwaisalqadri.mangaku.data.souce.local.entity

import io.realm.RealmObject

class MangaObject: RealmObject {
    var attributes: AttributesObject? = null
    var mangaId: String = ""
    var type: String = ""
}