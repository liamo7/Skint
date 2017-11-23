package com.loh.skint.util

const val DECIMAL_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$"

fun String.isValidDecimal(): Boolean {
    return !isBlank() && matches(Regex(DECIMAL_REGEX))
}