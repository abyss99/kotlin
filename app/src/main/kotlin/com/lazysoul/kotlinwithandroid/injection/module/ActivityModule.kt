package com.lazysoul.kotlinwithandroid.injection.module

import android.app.Activity
import com.lazysoul.kotlinwithandroid.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Created by abyss on 05/08/2017.
 */
@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }
}