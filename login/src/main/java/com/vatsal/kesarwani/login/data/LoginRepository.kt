package com.vatsal.kesarwani.login.data

import com.vatsal.kesarwani.login.data.network.LoginApiService
import com.vatsal.kesarwani.login.data.request.EmailOtpRequest
import com.vatsal.kesarwani.login.data.request.LoginRequest
import com.vatsal.kesarwani.login.data.response.EmailOtpResponse
import com.vatsal.kesarwani.login.data.response.LoginResponse
import io.reactivex.Single
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: LoginApiService) {

    fun login(body : LoginRequest) : Single<LoginResponse> {
        return apiService.login(body)
    }

    fun mobileOtp(body: EmailOtpRequest) : Single<EmailOtpResponse> {
        return apiService.verifyMobileOtp(body)
    }

}