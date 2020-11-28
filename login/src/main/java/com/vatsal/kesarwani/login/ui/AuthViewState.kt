package com.vatsal.kesarwani.login.ui

import com.vatsal.kesarwani.login.utils.VerifyType

sealed class AuthViewState {

    object GoToLoginScreen : AuthViewState()

    data class GoToOtpScreen(val data : String, val type : VerifyType) : AuthViewState()

    object NewUserAccount : AuthViewState()

    object ReLoginUser : AuthViewState()
}