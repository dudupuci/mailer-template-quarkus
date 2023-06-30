package com.templates.models.facade

import com.templates.models.entities.SenderAttempt
import com.templates.models.usecases.base.UseCase

interface SenderAttemptFacade {
    fun save(senderAttempt: SenderAttempt)
}