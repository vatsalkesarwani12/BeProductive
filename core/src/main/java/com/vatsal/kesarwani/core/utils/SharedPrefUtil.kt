package com.vatsal.kesarwani.core.utils

import android.app.Application
import com.vatsal.kesarwani.core.network.localstorage.LocalStorageKey
import com.vatsal.kesarwani.core.network.localstorage.SharedPreferenceHelper
import com.vatsal.kesarwani.core.network.localstorage.SharedPreferenceHelperImpl
import javax.inject.Inject

class SharedPrefUtil @Inject constructor(application: Application) {

    private val mSharePreferences: SharedPreferenceHelper = SharedPreferenceHelperImpl(application)

    /**
     * flag for check whether user logged in or not.
     */
    var isLoggedIn: Boolean = false
        get() {
            return mSharePreferences.getBoolean(LocalStorageKey.IS_LOGGED_IN.name, false)
        }
        set(value) {
            field = value
            mSharePreferences.setBoolean(LocalStorageKey.IS_LOGGED_IN.name, value)
        }

    /**
     * auth key for headers.
     */
    var authKey: String = ""
        get() {
            return mSharePreferences.getString(LocalStorageKey.BEARER_TOKEN.name)
        }
        set(value) {
            field = value
            mSharePreferences.setString(LocalStorageKey.BEARER_TOKEN.name, value)
        }

    /**
     * FCM Device token
     */
    var deviceToken: String = ""
        get() {
            return mSharePreferences.getString(LocalStorageKey.DEVICE_TOKEN.name)
        }
        set(value) {
            field = value
            mSharePreferences.setString(LocalStorageKey.DEVICE_TOKEN.name, value)
        }

    /**
     * Email Address
     */
    var loginEmail: String = ""
        get() {
            return mSharePreferences.getString(LocalStorageKey.LOGIN_EMAIL.name)
        }
        set(value) {
            field = value
            mSharePreferences.setString(LocalStorageKey.LOGIN_EMAIL.name, value)
        }

    fun clearSharedPref() {
        mSharePreferences.clear()
    }
}
