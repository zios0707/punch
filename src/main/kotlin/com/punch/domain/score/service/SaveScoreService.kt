package com.punch.domain.score.service

import com.punch.domain.score.domain.Score
import com.punch.domain.score.domain.repository.ScoreRepository
import com.punch.domain.score.presentation.request.SaveScoreRequest
import com.punch.domain.user.exception.UserNotFoundException
import com.punch.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class SaveScoreService (
    private val scoreRepository: ScoreRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: SaveScoreRequest) {

        val user = try {
            userFacade.findByNickname(request.nickname)
        }catch(e: UserNotFoundException) {
            userFacade.makeNewUser(request.nickname)
        }

        scoreRepository.save(
            Score(
                id = 0,
                user = user,
                score = request.score,
                scoredAt = Timestamp(System.currentTimeMillis())
            )
        )
    }
}