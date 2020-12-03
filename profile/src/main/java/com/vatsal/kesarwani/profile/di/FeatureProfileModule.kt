package com.vatsal.kesarwani.profile.di

import com.vatsal.kesarwani.profile.data.ProfileRepository
import com.vatsal.kesarwani.profile.data.network.ProfileAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(
        includes = [
            ActivityBindingModule::class,
            ViewModelBindingModule::class
        ]
)
class FeatureProfileModule {

    @Provides
    fun provideProfileAPIService(retrofit: Retrofit): ProfileAPIService {
        return retrofit.create(ProfileAPIService::class.java)
    }

    @Provides
    fun provideProfileRepo(profileAPIService: ProfileAPIService): ProfileRepository {
        return ProfileRepository(profileAPIService)
    }

}