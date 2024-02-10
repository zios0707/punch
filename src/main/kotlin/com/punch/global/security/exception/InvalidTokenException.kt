package com.punch.global.security.exception

import com.punch.global.error.ErrorCode
import com.punch.global.error.exception.BusinessException

class InvalidTokenException private constructor() : BusinessException(SecurityErrorCode.TOKEN_WAS_INVALID) {
    companion object {
        @JvmField
        val EXCEPTION = InvalidTokenException()
    }
}