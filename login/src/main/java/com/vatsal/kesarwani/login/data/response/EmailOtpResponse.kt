package com.vatsal.kesarwani.login.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
class EmailOtpResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("data")
    var data: Data? = null

) : Parcelable {

    @Parcelize
    data class Data(

        @SerializedName("Acc_Status")
        var accStatus: String? = null,

        @SerializedName("MSG")
        var mSG: String? = null,

        @SerializedName("token")
        var token: String? = null

    ) : Parcelable
}