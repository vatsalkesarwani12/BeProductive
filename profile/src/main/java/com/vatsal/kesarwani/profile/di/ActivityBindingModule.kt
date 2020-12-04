package com.vatsal.kesarwani.profile.di

import com.vatsal.kesarwani.profile.ui.editProfile.EditProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun injectEditProfileActivity() : EditProfileActivity

}