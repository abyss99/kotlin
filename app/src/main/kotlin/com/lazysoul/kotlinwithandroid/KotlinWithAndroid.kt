package com.lazysoul.kotlinwithandroid

import android.app.Application
import com.lazysoul.kotlinwithandroid.injection.components.ApplicationComponent
import com.lazysoul.kotlinwithandroid.injection.components.DaggerApplicationComponent
import com.lazysoul.kotlinwithandroid.injection.module.ApplicationModule
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by abyss on 05/08/2017.
 */
class KotlinWithAndroid : Application() {
    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }

    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }
}