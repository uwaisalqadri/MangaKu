package com.uwaisalqadri.mangaapp.data.souce.local.entity

import com.uwaisalqadri.mangaapp.data.souce.remote.response.AttributesItem
import io.realm.RealmObject

class MangaObject: RealmObject {
    var attributes: AttributesObject? = null
    var mangaId: String = ""
    var type: String = ""
}