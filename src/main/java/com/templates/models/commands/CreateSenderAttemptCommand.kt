package com.templates.models.commands

import java.time.Instant

data class CreateSenderAttemptCommand(
        val isSent: Boolean = false,
        val descriptionError: String = "",
        val attemptMoment: Instant = Instant.now()
) {
}