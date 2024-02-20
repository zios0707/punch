package com.punch.domain.score.service

import com.punch.domain.score.domain.QScore
import com.punch.domain.score.presentation.reponse.ScoresResponse
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service

@Service
class QueryScoresByNicknameService (
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun execute(nickname : String) : Array<ScoresResponse> {

        val score = QScore.score1

        var response : Array<ScoresResponse> = emptyArray()

        jpaQueryFactory
            .select(
                score.user,
                score.score
            )
            .from(score)
            .where(score.user.nickname.eq(nickname))
            .orderBy(score.user.nickname.desc())
            .fetch()
            .stream().forEach {
                response = response.plus(ScoresResponse(it.get(score.score), it.get(score.user)))
            }

        return response
    }
}