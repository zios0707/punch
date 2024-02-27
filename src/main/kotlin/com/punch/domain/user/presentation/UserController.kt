package com.punch.domain.user.presentation

import com.punch.domain.user.domain.User
import com.punch.domain.user.presentation.request.LoginRequest
import com.punch.domain.user.presentation.request.ModifyProfileRequest
import com.punch.domain.user.presentation.response.LoginResponse
import com.punch.domain.user.service.GetProfileService
import com.punch.domain.user.service.LoginService
import com.punch.domain.user.service.ModifyProfileService
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RestController
class UserController (
    private val getProfileService: GetProfileService,
    private val modifyProfileService: ModifyProfileService,
    private val loginService: LoginService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest) : LoginResponse {
        return loginService.execute(request)
    }

    @GetMapping("/{nickname}")
    fun getProfile(@PathVariable nickname: String) : User {
        return getProfileService.execute(nickname)
    }

    @PatchMapping("/{nickname}")
    fun modifyProfile(@PathVariable nickname: String, request: ModifyProfileRequest) {
        modifyProfileService.execute(nickname, request)
    }
}