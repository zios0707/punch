package com.punch.global.security.jwt

import com.punch.global.security.auth.AuthDetailsService
import com.punch.global.security.exception.ExpiredTokenException
import com.punch.global.security.exception.InvalidTokenException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider (

    @Value("\${jwt.secret}")
    private val secretKey: String,
    @Value("\${jwt.header")
    private val header: String,
    @Value("\${jwt.prefix}")
    private val prefix: String,
    @Value("\${jwt.access}")
    private val accessExp: Long,
    @Value("\${jwt.refresh}")
    private val refreshExp: Long,

    private val authDetailsService: AuthDetailsService
) {
    fun createAccessToken(nickname: String): String {
        return createToken(nickname, "access", accessExp)
    }
    fun createRefreshToken(nickname: String): String {
        // TODO : 리프레시 토큰 저장소 생성, 저장 구문 만들기
        return createToken(nickname, "refresh", refreshExp)
    }

    private fun createToken(username: String, jwtType: String, exp: Long): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()), SignatureAlgorithm.HS256)
            .setSubject(username)
            .setHeaderParam(header, jwtType)
            .setId(username)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

    fun getAuthentication(token: String): Authentication {

        val claims = getClaims(token)
        if (claims.header[Header.JWT_TYPE] == "access") {
            throw InvalidTokenException.EXCEPTION
        }

        val details = authDetailsService.loadUserByUsername(claims.body.id)
        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is ExpiredTokenException -> throw ExpiredTokenException.EXCEPTION
                else -> throw InvalidTokenException.EXCEPTION
            }
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {

        val bearerToken = request.getHeader(header)

        if (bearerToken != null && (bearerToken.startsWith(header))) {
            return bearerToken.substring(7)
        }
        return null
    }
}