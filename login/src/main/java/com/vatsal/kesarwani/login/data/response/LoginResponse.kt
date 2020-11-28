package com.vatsal.kesarwani.login.data.response


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
class LoginResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("data")
    var data: String? = null

) : Parcelable