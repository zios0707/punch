package com.punch.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.punch.global.security.jwt.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig (
    private val jwtProvider: JwtProvider,
    private val objectMapper: ObjectMapper
) {
    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.cors().and()
            .csrf().disable()
            .formLogin().disable()

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.apply(FilterConfig(jwtProvider, objectMapper))

        return http.build()
    }


}