package com.punch.global.security.auth

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import com.punch.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService (
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(nickname: String): UserDetails {
        val user: User = userRepository.findByNickname(nickname) ?: throw UserNotFoundException.EXCEPTION
        return AuthDetails(user)
    }
}