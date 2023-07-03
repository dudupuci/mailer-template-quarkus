package com.templates.models.usecases.impl

import com.templates.models.commands.CreateEmailCommand
import com.templates.models.entities.Email
import com.templates.models.repositories.EmailRepository
import com.templates.models.repositories.SenderAttemptRepository
import com.templates.models.usecases.CreateEmailUseCase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateEmailUseCaseImpl(
        private val repository: EmailRepository,
        private val senderAttemptRepository: SenderAttemptRepository,
) : CreateEmailUseCase {
    override fun execute(input: CreateEmailCommand): Email {
        val email = Email.from(input)
        try {
            for (attempt in input.attempts) {
                this.senderAttemptRepository.save(attempt)
            }
            this.repository.save(email)
        } catch (err: Exception) {
            throw RuntimeException("cannot_save_email " + err.message)
        }
        return email
    }
}