package com.punch.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.punch.global.error.GlobalExceptionFilter
import com.punch.global.security.jwt.JwtFilter
import com.punch.global.security.jwt.JwtProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtProvider : JwtProvider,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    @Override
    public override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}