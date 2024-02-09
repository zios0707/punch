package com.punch.global.error.dto

import com.punch.global.error.GlobalErrorCode
import com.punch.global.error.exception.ErrorProperty
import jakarta.validation.ConstraintViolationException
import org.springframework.validation.BindingResult

data class ErrorResponse(
    val status: Int,
    val message: String,
    val fieldErrors : List<CustomFieldError>
) {
    companion object {
        fun of(errorCode: ErrorProperty) = ErrorResponse(
            status = errorCode.status(),
            message = errorCode.message(),
            fieldErrors = emptyList()
        )

        fun of(bindingResult: BindingResult) : ErrorResponse = of(
            exception = GlobalErrorCode.BAD_REQUEST,
            fieldErrors = CustomFieldError.of(bindingResult)
        )


        private fun of(exception: ErrorProperty, fieldErrors: List<CustomFieldError>) = ErrorResponse(
            status = exception.status(),
            message = exception.message(),
            fieldErrors = fieldErrors
        )
    }
}
