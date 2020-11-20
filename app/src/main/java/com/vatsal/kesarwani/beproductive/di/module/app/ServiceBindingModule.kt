package com.vatsal.kesarwani.beproductive.di.module.app

import com.vatsal.kesarwani.beproductive.services.MyFirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBindingModule {

    @ContributesAndroidInjector
    abstract fun injectMyFirebaseService() : MyFirebaseMessagingService
}