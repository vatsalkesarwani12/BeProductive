package com.vatsal.kesarwani.profile.data

import com.vatsal.kesarwani.profile.data.network.ProfileAPIService
import javax.inject.Inject

class ProfileRepository @Inject constructor(
        private val apiService: ProfileAPIService
) {


}