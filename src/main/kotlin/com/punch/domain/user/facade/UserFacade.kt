package com.punch.domain.user.facade

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import com.punch.domain.user.exception.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserFacade (
    private val userRepository: UserRepository
) {
    fun findByNickname(nickname: String) : User {
        return userRepository.findByNickname(nickname) ?: throw UserNotFoundException.EXCEPTION
    }

    @Transactional
    fun makeNewUser(nickname: String) : User {
        val newUser = User(0, nickname, "1234")
        userRepository.save(newUser)
        return newUser
    }

    fun getUserByToken() : User? {
        try {
            val authentication = SecurityContextHolder.getContext().authentication
            val nickname = authentication.name
            return userRepository.findByNickname(nickname) ?: throw UserNotFoundException.EXCEPTION
        } catch (e: Exception) {
            return null
        }
    }
}