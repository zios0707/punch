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
        val user: User = userFacade.findByNickname(nickname)

        val password = request.password.takeUnless { it.isBlank() } ?: user.password
        val image_url = request.password.takeUnless { it.isBlank() } ?: user.image_url

        val changedUser = User(
            id = user.id,
            nickname = user.nickname,
            password = password,
            image_url = image_url,
        )

        userRepository.save(changedUser)
    }
}