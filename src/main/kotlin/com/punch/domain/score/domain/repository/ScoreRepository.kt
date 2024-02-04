package com.punch.domain.score.domain.repository

import com.punch.domain.score.domain.Score
import org.springframework.data.repository.CrudRepository

interface ScoreRepository : CrudRepository<Score, Long> {

}