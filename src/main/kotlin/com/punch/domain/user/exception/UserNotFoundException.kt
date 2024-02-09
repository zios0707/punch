package com.punch.domain.user.exception

import com.punch.global.error.ErrorCode
import com.punch.global.error.exception.BusinessException

class UserNotFoundException private constructor() : BusinessException(ErrorCode.USER_NOT_FOUND) {
    companion object {
        @JvmField
        val EXCEPTION = UserNotFoundException()
    }
}