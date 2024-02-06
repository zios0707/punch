package com.punch.domain.user.presentation.request

import jakarta.validation.constraints.NotBlank

data class LoginRequest (

    @field:NotBlank
    val nickname: String,

    @field:NotBlank
    val password: String

)