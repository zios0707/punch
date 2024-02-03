package com.punch.global.entity

import jakarta.persistence.*

@MappedSuperclass
abstract class BaseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0
)