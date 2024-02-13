package com.punch.domain.score.presentation.reponse

import com.punch.domain.user.domain.User

data class ScoresResponse (

    val scores: Int?,
    val user: User?

)