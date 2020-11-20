package com.vatsal.kesarwani.beproductive.di.module

import androidx.lifecycle.ViewModelProvider
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}