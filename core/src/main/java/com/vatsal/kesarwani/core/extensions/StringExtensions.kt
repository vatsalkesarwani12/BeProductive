package com.vatsal.kesarwani.core.extensions

import android.text.Editable
import android.text.Html
import android.text.Spanned
import java.util.*
import java.util.regex.Pattern.compile

fun String?.isValid(): Boolean {
    return this != null && this.isNotEmpty() && this.isNotBlank()
}

fun String?.isNotValid(): Boolean {
    return !this.isValid()
}

fun String.space(string: String?): String {
    return this.plus(" ${string ?: ""}")
}

fun String.newLineWithBullet(): String {
    return "".plus("\n \n").plus("\u25cf  ").plus(this)
}

fun String.toBoldHtml(): String {
    return "<b>".plus(this).plus("</b>")
}

fun String.toFormattedInt(): String {

    return if (this.contains(".00")) {
        this.toDouble().toInt().toString()
    } else
        this.toDouble().toString()
}

private val emailRegex = compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun String?.isEmail(): Boolean {
    return this.isValid() && emailRegex.matcher(this?.trimEnd()?.trimStart()).matches()
}

fun String?.isPhoneNumber(): Boolean {
    return this.isValid() && IntRange(10, 10).contains(this?.trimEnd()?.trimStart()?.length)
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun String.toHtmlString(): Spanned {
    return Html.fromHtml(this)
}

fun Float.roundTo(decimalPlaces: Int): Float {
    return "%.${decimalPlaces}f".format(Locale.ENGLISH,this).toFloat()
}
