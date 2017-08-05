package com.lazysoul.kotlinwithandroid.common

/**
 * Created by abyss on 05/08/2017.
 */
interface BaseMvpPresenter<in T : BaseMvpView> {

    fun attachView(view: T)

    fun destroy()
}