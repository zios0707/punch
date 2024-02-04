package com.punch.domain.user.service

import com.punch.domain.user.domain.User
import com.punch.domain.user.domain.repository.UserRepository
import com.punch.domain.user.facade.UserFacade
import com.punch.domain.user.presentation.request.ModifyProfileRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ModifyProfileService (
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute(nickname: String, request: ModifyProfileRequest) {
        var user: User = userFacade.findByNickname(nickname)

        //TODO : 이미지 URL 추가

        if (request.password != null) user.password = request.password

        userRepository.save(user)
    }
}