package com.lazysoul.kotlinwithandroid.injection.components

import android.content.SharedPreferences
import com.lazysoul.kotlinwithandroid.injection.module.ApplicationModule
import com.lazysoul.kotlinwithandroid.KotlinWithAndroid
import dagger.Component
import javax.inject.Singleton

/**
 * Created by abyss on 05/08/2017.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun injet(kotlinWithAndroid: KotlinWithAndroid)

    fun getSharedPreferences(): SharedPreferences
    fun getEditor(): SharedPreferences.Editor
}