package com.vatsal.kesarwani.login.di.module

import androidx.lifecycle.ViewModel
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelKey
import com.vatsal.kesarwani.login.ui.AuthViewModel
import com.vatsal.kesarwani.login.ui.fragment.login.LoginViewModel
import com.vatsal.kesarwani.login.ui.fragment.otp.OtpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel : LoginViewModel) : ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(OtpViewModel::class)
    abstract fun bindOtpViewModel(viewModel : OtpViewModel) : ViewModel?

}