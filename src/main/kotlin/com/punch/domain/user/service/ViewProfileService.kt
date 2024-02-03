package com.punch.domain.user.service

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service

class ViewProfileService (
    private val userRepository: UserRepository
) {
    fun execute(nickname: String) : User {
        val user: User = userRepository.findByNickname(nickname) ?: throw RuntimeException("404 :: 해당 유저가 존재하지 않습니다.")

        return user
    }
}