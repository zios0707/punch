package com.punch.global.security.exception

import com.punch.global.error.exception.ErrorProperty

enum class SecurityErrorCode (
    private val status: Int,
    private val message: String
) : ErrorProperty {
    TOKEN_WAS_EXPIRED(401 , "Expired Token"),
    TOKEN_WAS_INVALID(401, "Invalid Token");

    override fun status(): Int = status
    override fun message(): String = message

}