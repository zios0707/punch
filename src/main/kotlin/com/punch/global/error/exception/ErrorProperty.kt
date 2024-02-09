package com.punch.global.error.exception

interface ErrorProperty {
    fun status() : Int
    fun message() : String
}