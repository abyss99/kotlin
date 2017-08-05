package com.lazysoul.kotlinwithandroid.singletons

import com.lazysoul.kotlinwithandroid.datas.Todo
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

/**
 * Created by abyss on 05/08/2017.
 */
object TodoManager {
    val KEY_ID: String = "id"
    val KEY_REQUEST_TYPE: String = "request_type"
    val KEY_RESULT_TYPE: String = "result_type"
    val REQUEST_TYPE_CREATE: Int = 100
    val REQUEST_TYPE_VIEW: Int = 101
    val RESULT_TYPE_CREATED: Int = 200
    val RESULT_TYPE_UPDATED: Int = 201


    @JvmStatic
    fun getTodoList(realm: Realm): RealmResults<Todo> {
        return realm.where(Todo::class.java).findAllSorted("id")
    }

    @JvmStatic
    fun load(realm: Realm, id: Int): Todo {
        return realm.where(Todo::class.java).equalTo("id", id).findFirst()
    }

    @JvmStatic
    fun createSamleTodo(realm: Realm) {
        realm.beginTransaction();
        for (i in 0 until 10) {
            val todo = realm.createObject(Todo::class.java, i)
            todo.isChecked = false
            todo.body = "Todo " + i
            todo.createdAt = Calendar.getInstance().time
        }
        realm.commitTransaction();
    }

    @JvmStatic
    fun getMaxId(realm: Realm) : Int {
        return realm.where(Todo::class.java)
                .max("id")
                .toInt()
    }

    @JvmStatic
    fun search(realm: Realm, text: String): RealmResults<Todo> {
        return realm.where(Todo::class.java)
                .contains("body", text, Case.INSENSITIVE)
                .findAll()
    }
}