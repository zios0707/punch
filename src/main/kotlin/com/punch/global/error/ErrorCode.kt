package com.punch.global.error

import com.punch.global.error.exception.ErrorProperty

enum class ErrorCode (
    private val status: Int,
    private val message: String
) : ErrorProperty {


    PASS_NOT_MATCH(401, "비밀번호가 일치하지 않습니다."),

    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다.");


    override fun status() : Int = status
    override fun message() : String = message
}