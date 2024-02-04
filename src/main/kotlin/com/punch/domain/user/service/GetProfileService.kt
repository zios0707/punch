package com.punch.domain.user.service

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import com.punch.domain.user.facade.UserFacade
import org.springframework.stereotype.Service

@Service

class GetProfileService (
    private val userFacade: UserFacade
) {
    fun execute(nickname: String) : User {
        return userFacade.findByNickname(nickname)
    }
}