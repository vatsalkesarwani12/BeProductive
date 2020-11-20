package com.vatsal.kesarwani.core.network.localstorage

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelperImpl(context: Context) : SharedPreferenceHelper {

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    override fun setString(key: String, value: String?) {
        value?.let {
            sharedPreferences
                .edit()
                .putString(key, value)
                .apply()
        }
    }

    override fun getString(key: String, default: String): String {
        return sharedPreferences.getString(key, default) ?: ""
    }

    override fun setFloat(key: String, value: Float) {
        sharedPreferences
            .edit()
            .putFloat(key, value)
            .apply()
    }

    override fun getFloat(key: String, default: Float): Float {
        return sharedPreferences.getFloat(key, default)
    }

    override fun setInt(key: String, value: Int) {
        sharedPreferences
            .edit()
            .putInt(key, value)
            .apply()
    }

    override fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    override fun setLong(key: String, value: Long?) {
        value?.let {
            sharedPreferences
                .edit()
                .putLong(key, value)
                .apply()
        }
    }

    override fun getLong(key: String, default: Long): Long {
        return sharedPreferences.getLong(key, default)
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "com.beProductive"
        fun getInstance(context: Context): SharedPreferenceHelperImpl {
            return SharedPreferenceHelperImpl(context)
        }
    }
}
