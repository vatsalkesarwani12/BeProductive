package com.vatsal.kesarwani.login.data.request

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
class EmailOtpRequest(

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("otp")
    var otp: Int? = null

) : Parcelable