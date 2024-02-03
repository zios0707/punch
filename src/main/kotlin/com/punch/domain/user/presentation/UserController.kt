package com.punch.domain.user.presentation

import com.punch.domain.user.domain.User
import com.punch.domain.user.service.ViewProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController (
    private val viewProfileService: ViewProfileService
) {
    @GetMapping("/{nickname}")
    fun viewProfile(@PathVariable nickname: String) : User {
        return viewProfileService.execute(nickname)
    }
}