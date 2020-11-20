package com.vatsal.kesarwani.core.network.localstorage

interface SharedPreferenceHelper {

    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, default: Boolean = false): Boolean

    fun setString(key: String, value: String?)
    fun getString(key: String, default: String = ""): String

    fun setFloat(key: String, value: Float)
    fun getFloat(key: String, default: Float = 0f): Float

    fun setInt(key: String, value: Int)
    fun getInt(key: String, default: Int = 0): Int

    fun setLong(key: String, value: Long?)
    fun getLong(key: String, default: Long = 0L): Long

    fun clear()
}
