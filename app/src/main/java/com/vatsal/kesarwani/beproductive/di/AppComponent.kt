package com.vatsal.kesarwani.beproductive.di

import android.app.Application
import com.vatsal.kesarwani.beproductive.di.module.AppModule
import com.vatsal.kesarwani.beproductive.di.module.ViewModelFactoryModule
import com.vatsal.kesarwani.login.di.FeatureLoginModule
import com.vatsal.kesarwani.profile.di.FeatureProfileModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        dagger.android.support.AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        FeatureLoginModule::class,
        FeatureProfileModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}