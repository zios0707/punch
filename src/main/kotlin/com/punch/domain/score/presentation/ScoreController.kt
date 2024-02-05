package com.punch.domain.score.presentation

import com.punch.domain.score.presentation.request.SaveScoreRequest
import com.punch.domain.score.service.SaveScoreService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/score")
@RestController
class ScoreController (
    private val saveScoreService: SaveScoreService
) {

    @PostMapping("/")
    fun saveScore(@RequestBody request: SaveScoreRequest) {
        saveScoreService.execute(request)
    }
}