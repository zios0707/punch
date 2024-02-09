package com.punch.domain.user.facade

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import com.punch.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class UserFacade (
    private val userRepository: UserRepository
) {
    fun findByNickname(nickname: String) : User {
        return userRepository.findByNickname(nickname) ?: throw UserNotFoundException.EXCEPTION
    }

    fun makeNewUser(nickname: String) : User {
        val newUser = User(0, nickname, "1234")
        userRepository.save(newUser)
        return newUser
    }
}