package com.punch.domain.user.presentation

import com.punch.domain.user.domain.User
import com.punch.domain.user.presentation.request.ModifyProfileRequest
import com.punch.domain.user.service.GetProfileService
import com.punch.domain.user.service.ModifyProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController (
    private val getProfileService: GetProfileService,
    private val modifyProfileService: ModifyProfileService
) {
    @GetMapping("/{nickname}")
    fun getProfile(@PathVariable nickname: String) : User {
        return getProfileService.execute(nickname)
    }

    @PatchMapping("/{nickname}")
    fun modifyProfile(@PathVariable nickname: String, request: ModifyProfileRequest) {
        modifyProfileService.execute(nickname, request)
    }
}