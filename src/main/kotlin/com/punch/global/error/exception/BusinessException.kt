package com.punch.global.error.exception

open class BusinessException (
    val errorProperty: ErrorProperty
) : RuntimeException() {
}