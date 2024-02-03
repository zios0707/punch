package com.punch.domain.user.domain.repository

import com.punch.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByNickname(nickname: String) : User?
}