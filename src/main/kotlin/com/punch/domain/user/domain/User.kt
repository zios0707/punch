package com.punch.domain.user.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.punch.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User (
    id : Long, // user_id

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val nickname: String,

    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val password: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    var image_url: String


) : BaseEntity(id)