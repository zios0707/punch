package com.punch.domain.user.presentation

import com.punch.domain.user.domain.User
import com.punch.domain.user.service.GetProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController (
    private val getProfileService: GetProfileService
) {
    @GetMapping("/{nickname}")
    fun getProfile(@PathVariable nickname: String) : User {
        return getProfileService.execute(nickname)
    }
}