package com.vatsal.kesarwani.login.di.module

import com.vatsal.kesarwani.login.ui.fragment.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun injectLoginFragment() : LoginFragment


}