package com.uwaisalqadri.mangaku.data.souce.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class MangaObject: RealmObject {
    @PrimaryKey
    var id: String = ""
    var attributes: AttributesObject? = null
    var type: String = ""
}