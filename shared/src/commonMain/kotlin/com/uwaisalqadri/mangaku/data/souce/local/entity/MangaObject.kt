package com.uwaisalqadri.mangaku.data.souce.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class MangaObject: RealmObject {
    var attributes: AttributesObject? = null
    @PrimaryKey var id: String = ""
    var type: String = ""
}