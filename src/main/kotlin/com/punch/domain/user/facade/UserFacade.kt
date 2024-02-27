package com.punch.domain.user.facade

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import com.punch.domain.user.exception.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserFacade (
    private val userRepository: UserRepository
) {

    @Value("\${user.password}")
    private lateinit var defaultPassword : String

    @Value("\${user.image-url}")
    private lateinit var defaultImageURL : String

    fun findByNickname(nickname: String) : User {
        return userRepository.findByNickname(nickname) ?: throw UserNotFoundException.EXCEPTION
    }

    @Transactional
    fun makeNewUser(nickname: String) : User {
        val newUser = User(0, nickname, defaultPassword, defaultImageURL)
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