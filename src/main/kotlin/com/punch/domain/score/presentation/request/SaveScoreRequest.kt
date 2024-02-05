package com.punch.domain.score.presentation.request

import jakarta.validation.constraints.NotBlank

data class SaveScoreRequest (

    @field:NotBlank
    val nickname: String,

    @field:NotBlank
    val score: Int

)