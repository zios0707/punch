package com.punch.domain.score.service

import com.punch.domain.score.domain.QScore
import com.punch.domain.score.presentation.reponse.ScoresResponse
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service

@Service
class GetRankingScoresService  (
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun execute() : Array<ScoresResponse> {
        val score = QScore.score1

        var response : Array<ScoresResponse> = emptyArray()


        jpaQueryFactory
            .select(
                score.user,
                score.score.max(),
            )
            .from(score)
            .groupBy(
                score.user,
                score.score)
            .orderBy(
                score.score.desc()
            )
            .limit(10L)
            .fetch()
            .stream().forEach {
                response = response.plus(ScoresResponse(it.get(score.score.max()), it.get(score.user)))
            }

        return response
    }
}