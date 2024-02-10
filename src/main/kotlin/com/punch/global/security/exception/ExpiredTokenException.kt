package com.punch.global.security.exception

import com.punch.global.error.exception.BusinessException

class ExpiredTokenException private constructor() : BusinessException(SecurityErrorCode.TOKEN_WAS_EXPIRED) {
    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}