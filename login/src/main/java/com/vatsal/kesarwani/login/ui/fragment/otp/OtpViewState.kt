package com.vatsal.kesarwani.login.ui.fragment.otp

import com.vatsal.kesarwani.login.data.response.EmailOtpResponse

sealed class OtpViewState {

    object Init : OtpViewState()

    object Loading : OtpViewState()

    object Verify : OtpViewState()

    object ResendOtp : OtpViewState()

    data class OnSuccessResend(var mssg : String?) : OtpViewState()

    data class OnSuccess(var emailOtpResponse: EmailOtpResponse) : OtpViewState()

    data class OnError(var mssg : String?) : OtpViewState()

    data class OnFieldError(var mssg : String?) : OtpViewState()
}
