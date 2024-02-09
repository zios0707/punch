package com.punch.global.error

import com.punch.global.error.exception.ErrorProperty
import org.springframework.http.HttpStatus

enum class GlobalErrorCode (
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력값이 잘못되었습니다."),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 요청 메소드입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 에러가 발생하였습니다.")
    ;

    override fun status(): Int = status.value()
    override fun message(): String = message
}