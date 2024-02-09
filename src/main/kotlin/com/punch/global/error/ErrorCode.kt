package com.punch.global.error

import com.punch.global.error.exception.ErrorProperty

enum class ErrorCode (
    private val status: Int,
    private val message: String
) : ErrorProperty {


    USER_NOT_FOUND(404, "유저가 찾아봐도 없는데요?");


    override fun status() : Int = status
    override fun message() : String = message
}