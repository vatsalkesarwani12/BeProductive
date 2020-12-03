package com.vatsal.kesarwani.login.di

import com.vatsal.kesarwani.login.data.LoginRepository
import com.vatsal.kesarwani.login.data.network.LoginApiService
import com.vatsal.kesarwani.login.di.module.ActivityBindingModule
import com.vatsal.kesarwani.login.di.module.FragmentBindingModule
import com.vatsal.kesarwani.login.di.module.LoginViewModelModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(
        includes= [
            ActivityBindingModule::class,
            FragmentBindingModule::class,
            LoginViewModelModule::class
        ]
)
class FeatureLoginModule {

    @Provides
    fun provideLoginAPIService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }

    @Provides
    fun provideLoginRepo(loginAPIService: LoginApiService): LoginRepository {
        return LoginRepository(loginAPIService)
    }
}