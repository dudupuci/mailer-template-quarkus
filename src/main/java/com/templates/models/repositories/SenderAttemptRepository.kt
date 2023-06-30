package com.templates.models.repositories

import com.templates.models.entities.SenderAttempt

interface SenderAttemptRepository {
    fun save(senderAttempt: SenderAttempt)
}