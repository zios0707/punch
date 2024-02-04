package com.punch.domain.user.domain

import com.punch.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User (
    id : Long, // user_id

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val nickname: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    var password: String

    //TODO : 이미지 URL 추가

) : BaseEntity(id)