package com.punch.domain.user.service

import com.punch.domain.user.domain.User
import com.punch.domain.user.exception.PassNotMatchException
import com.punch.domain.user.facade.UserFacade
import com.punch.domain.user.presentation.request.LoginRequest
import com.punch.domain.user.presentation.response.LoginResponse
import com.punch.global.security.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class LoginService (
    private val userFacade: UserFacade,
    private val jwtProvider: JwtProvider
) {
    fun execute(request: LoginRequest) : LoginResponse {

        val user: User = userFacade.findByNickname(request.nickname)

        if(user.password != request.password) throw PassNotMatchException.EXCEPTION

        val accessToken = jwtProvider.createAccessToken(request.nickname)
        val refreshToken = jwtProvider.createRefreshToken(request.nickname)

        return LoginResponse(accessToken, refreshToken)
    }
}