package com.vatsal.kesarwani.login.di.module

import com.vatsal.kesarwani.login.ui.fragment.login.LoginFragment
import com.vatsal.kesarwani.login.ui.fragment.otp.OtpFragment
import com.vatsal.kesarwani.login.ui.fragment.started.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun injectStartFragment() : StartFragment

    @ContributesAndroidInjector
    abstract fun injectLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun injectOtpFragment() : OtpFragment

}