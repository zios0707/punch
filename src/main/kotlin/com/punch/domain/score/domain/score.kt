package com.punch.domain.score.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.sql.Timestamp

@RedisHash
class score (
    userId: Long,
    score: Int,
    scoredAt: Timestamp
) {
    @Id
    var userId: Long = userId
        protected set

    @Indexed
    var score: Int = score
        protected set

    var scoredAt: Timestamp = Timestamp(System.currentTimeMillis())
        protected set

}