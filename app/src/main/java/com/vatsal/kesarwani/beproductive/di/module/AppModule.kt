package com.vatsal.kesarwani.beproductive.di.module

import com.vatsal.kesarwani.beproductive.di.module.app.ActivityBindingModule
import com.vatsal.kesarwani.beproductive.di.module.app.NetworkModule
import com.vatsal.kesarwani.beproductive.data.AppRepository
import com.vatsal.kesarwani.beproductive.data.network.AppApiService
import com.vatsal.kesarwani.beproductive.di.module.app.AppViewModelModule
import com.vatsal.kesarwani.beproductive.di.module.app.ServiceBindingModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(
    includes= [
        ActivityBindingModule::class,
        NetworkModule::class,
        ServiceBindingModule::class,
        AppViewModelModule::class
    ]
)
class AppModule {

    @Provides
    fun provideAppApiService(retrofit: Retrofit): AppApiService {
        return retrofit.create(AppApiService::class.java)
    }

    @Provides
    fun provideAppRepository(appApiService: AppApiService): AppRepository {
        return AppRepository(appApiService)
    }

}