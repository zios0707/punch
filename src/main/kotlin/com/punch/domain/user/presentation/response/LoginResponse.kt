package com.punch.domain.user.presentation.response

data class LoginResponse (

    val accessToken: String,
    val refreshToken: String

)