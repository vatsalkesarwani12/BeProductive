package com.vatsal.kesarwani.beproductive.di.module.app

import android.app.Application
import com.vatsal.kesarwani.core.network.factory.BeProductiveServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(application: Application): Retrofit {
        return BeProductiveServiceFactory(application).create()
    }
}
