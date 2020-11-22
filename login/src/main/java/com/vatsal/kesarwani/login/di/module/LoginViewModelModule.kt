package com.vatsal.kesarwani.login.di.module

import androidx.lifecycle.ViewModel
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelKey
import com.vatsal.kesarwani.login.ui.fragment.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel : LoginViewModel) : ViewModel?

}