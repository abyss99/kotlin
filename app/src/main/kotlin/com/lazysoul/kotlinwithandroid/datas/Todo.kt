package com.lazysoul.kotlinwithandroid.datas

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by abyss on 05/08/2017.
 */
open class Todo : RealmObject() {
    @PrimaryKey
    var id: Int = -1

    var body: String = ""

    var isChecked: Boolean = false

    var createdAt: Date? = null

    @Ignore
    var isFixed: Boolean = false

}