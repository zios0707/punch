package com.punch.domain.user.exception

import com.punch.global.error.ErrorCode
import com.punch.global.error.exception.BusinessException

class PassNotMatchException private constructor() : BusinessException(ErrorCode.PASS_NOT_MATCH) {
    companion object {
        @JvmField
        val EXCEPTION = PassNotMatchException()
    }
}