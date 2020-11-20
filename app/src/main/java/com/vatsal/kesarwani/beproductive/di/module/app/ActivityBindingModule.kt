package com.vatsal.kesarwani.beproductive.di.module.app

import com.vatsal.kesarwani.beproductive.ui.MainActivity
import com.vatsal.kesarwani.beproductive.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectSplashActivity(): SplashActivity

    /*@ContributesAndroidInjector
    abstract fun injectNotificationActivity(): NotificationsActivity*/

}
