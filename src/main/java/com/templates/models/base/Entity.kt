package com.templates.models.base

import java.time.Instant
import java.util.*

abstract class Entity(
        val id: UUID = UUID.randomUUID(),
        val createdAt: Instant = Instant.now(),
        val updatedAt: Instant = Instant.now(),
) {
}