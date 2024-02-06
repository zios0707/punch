package com.punch.domain.user.service

import com.punch.domain.user.domain.User
import com.punch.domain.user.facade.UserFacade
import com.punch.domain.user.presentation.request.LoginRequest
import com.punch.domain.user.presentation.response.LoginResponse
import org.springframework.stereotype.Service

@Service
class LoginService (
    private val userFacade: UserFacade
) {
    fun execute(request: LoginRequest) : LoginResponse {
        val user: User = userFacade.findByNickname(request.nickname)

        if(user.password != request.password) throw RuntimeException("비밀번호가 일치하지 않습니다.")

        // TODO : JWT 토큰 발급하기
        val accessToken: String = "1234"
        val refreshToken: String = "1234"

        return LoginResponse(accessToken, refreshToken)
    }
}