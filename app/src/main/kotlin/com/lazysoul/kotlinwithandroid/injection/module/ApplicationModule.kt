package com.lazysoul.kotlinwithandroid.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by abyss on 05/08/2017.
 */
@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideSharedPrefs(): SharedPreferences {
        return application.getSharedPreferences("kotlinWithAndroid", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideEditor(pref: SharedPreferences): SharedPreferences.Editor {
        return pref.edit()
    }
}