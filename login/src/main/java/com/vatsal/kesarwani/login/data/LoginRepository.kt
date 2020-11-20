package com.vatsal.kesarwani.login.data

import com.vatsal.kesarwani.login.data.network.LoginApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: LoginApiService) {
}