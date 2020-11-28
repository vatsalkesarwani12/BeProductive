package com.vatsal.kesarwani.login.data.request

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
class LoginRequest(

    @SerializedName("email")
    var email: String? = null

) : Parcelable