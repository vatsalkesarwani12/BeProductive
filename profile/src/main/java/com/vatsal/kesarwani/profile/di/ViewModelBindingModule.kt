package com.vatsal.kesarwani.profile.di

import androidx.lifecycle.ViewModel
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelKey
import com.vatsal.kesarwani.profile.ui.editProfile.EditProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindsEditProfileViewModel(viewModel: EditProfileViewModel): ViewModel?
}