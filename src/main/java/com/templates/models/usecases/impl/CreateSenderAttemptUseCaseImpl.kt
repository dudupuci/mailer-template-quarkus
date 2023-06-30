package com.templates.models.usecases.impl

import com.templates.models.commands.CreateSenderAttemptCommand
import com.templates.models.entities.SenderAttempt
import com.templates.models.repositories.SenderAttemptRepository
import com.templates.models.usecases.CreateSenderAttemptUseCase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateSenderAttemptUseCaseImpl(
        private val repository: SenderAttemptRepository
) : CreateSenderAttemptUseCase {
    override fun execute(input: CreateSenderAttemptCommand): SenderAttempt {
        val attempt = SenderAttempt.from(input)
        this.repository.save(attempt)
        return attempt
    }
}