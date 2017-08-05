package com.lazysoul.kotlinwithandroid.injection.components

import com.lazysoul.kotlinwithandroid.injection.module.ActivityModule
import com.lazysoul.kotlinwithandroid.injection.scope.ActivityScope
import com.lazysoul.kotlinwithandroid.ui.detail.DetailActivity
import com.lazysoul.kotlinwithandroid.ui.main.MainActivity
import dagger.Component
import io.realm.Realm

/**
 * Created by abyss on 05/08/2017.
 */
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
@ActivityScope
interface ActivityComponent : ApplicationComponent {

    fun provideRealm(): Realm

    fun inject(activity: MainActivity)

    fun inject(activity: DetailActivity)
}