package com.punch.domain.score.domain

import com.punch.domain.user.domain.User
import com.punch.global.entity.BaseEntity
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
class Score (
    id: Long, // score_id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: User,

    @Column(columnDefinition = "SMALLINT", nullable = false)
    val score: Int,

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    val scoredAt: Timestamp


) : BaseEntity(id)