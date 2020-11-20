package com.vatsal.kesarwani.beproductive

import com.facebook.stetho.Stetho
import com.vatsal.kesarwani.core.utils.timber.DebugTree
import com.vatsal.kesarwani.core.utils.timber.ReleaseTree
import com.vatsal.kesarwani.beproductive.di.AppComponent
import com.vatsal.kesarwani.beproductive.di.DaggerAppComponent
import com.vatsal.kesarwani.beproductive.navigation.Navigator
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber

class MyApplication : DaggerApplication() {

    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        setDebugTools()
        setNavigation()
    }

    private fun setDebugTools() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Stetho.initializeWithDefaults(this)
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    private fun setNavigation() {
        XInjectionManager.init(this)
        XInjectionManager.bindComponentToCustomLifecycle(object : IHasComponent<Navigator> {
            override fun getComponent(): Navigator = Navigator()
        })
    }


    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        return appComponent
    }

}