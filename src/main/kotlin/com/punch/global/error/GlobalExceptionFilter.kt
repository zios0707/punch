package com.punch.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import com.punch.global.error.dto.ErrorResponse
import com.punch.global.error.exception.BusinessException
import com.punch.global.error.exception.ErrorProperty
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter


// Handler 방식이 아닌 Filter 방식. 얘도 되는구나
class GlobalExceptionFilter (
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal( // 요청마다 처리할 사항 설정 (JWT 토큰 뜯을 때도 사용됨)
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response) // 이 필터에서는 에러사항만 처리해줄 것이니 에러가 발생하지 않는다면 그냥 넘김
        }catch (e: BusinessException) {  // 직접 설정한 에러 발생시 직접 처리 (글로벌 처리는 자동인듯?)
            val errorCode: ErrorProperty = e.errorProperty
            response.status = errorCode.status()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val errorResponse: ErrorResponse = ErrorResponse.of(errorCode)
            objectMapper.writeValue(response.writer, errorResponse)
        }
    }
}