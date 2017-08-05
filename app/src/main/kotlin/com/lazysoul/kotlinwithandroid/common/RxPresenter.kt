package com.lazysoul.kotlinwithandroid.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by abyss on 05/08/2017.
 */
open class RxPresenter(var disposables:CompositeDisposable? = CompositeDisposable()) {

    fun dispose() {
        if(disposables!!.isDisposed) {
            disposables!!.dispose()
            disposables = null
        }
    }

    fun add(disposable: Disposable) {
        disposables!!.add(disposable)
    }
}