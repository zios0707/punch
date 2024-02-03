package com.punch.domain.user.domain

import com.punch.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User (
    id : Long,

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    val userId: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val nickname: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val password: String,

) : BaseEntity(id)