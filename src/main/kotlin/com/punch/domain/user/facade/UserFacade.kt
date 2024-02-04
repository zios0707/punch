package com.punch.domain.user.facade

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserFacade (
    private val userRepository: UserRepository
) {
    fun findByNickname(nickname: String) : User {
        return userRepository.findByNickname(nickname) ?: throw RuntimeException("404 :: 해당 유저가 존재하지 않습니다.")
    }
}