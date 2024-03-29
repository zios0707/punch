package com.punch.domain.score.presentation

import com.punch.domain.score.presentation.reponse.ScoresResponse
import com.punch.domain.score.presentation.request.SaveScoreRequest
import com.punch.domain.score.service.GetRankingScoresService
import com.punch.domain.score.service.QueryScoresByNicknameService
import com.punch.domain.score.service.SaveScoreService
import org.springframework.web.bind.annotation.*

@RequestMapping("/score")
@RestController
class ScoreController (
    private val saveScoreService: SaveScoreService,
    private val getRankingScoresService: GetRankingScoresService,
    private val queryScoresByNicknameService: QueryScoresByNicknameService
) {

    @PostMapping("/")
    fun saveScore(@RequestBody request: SaveScoreRequest) {
        saveScoreService.execute(request)
    }

    @GetMapping("/query")
    fun getRankingScores(): Array<ScoresResponse> {
        return getRankingScoresService.execute()
    }

    @GetMapping("/query/{nickname}")
    fun queryScoresByNickname(@PathVariable nickname : String): Array<ScoresResponse> {
        return queryScoresByNicknameService.execute(nickname)
    }
}