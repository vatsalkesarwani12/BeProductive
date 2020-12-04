package com.vatsal.kesarwani.beproductive.navigation

import android.content.Context
import com.vatsal.kesarwani.core.navigation.BaseNavigator
import com.vatsal.kesarwani.login.navigation.LoginNavigation
import com.vatsal.kesarwani.profile.navigation.ProfileNavigation
import com.vatsal.kesarwani.profile.ui.editProfile.EditProfileActivity

class Navigator : BaseNavigator(), AppNavigation, LoginNavigation, ProfileNavigation {

    override fun gotoEditProfile(context : Context) {
        EditProfileActivity.start(context)
    }

    override fun gotoDashBoard() {

    }

}