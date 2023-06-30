package com.templates.models.usecases.impl

import com.templates.models.entities.Email
import com.templates.models.repositories.EmailRepository
import com.templates.models.usecases.FindEmailByIdUseCase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class FindEmailByIdUseCaseImpl(
        private val repository: EmailRepository
) : FindEmailByIdUseCase {
    override fun execute(input: UUID): Email {
        return this.repository.findById(input)
                ?: throw RuntimeException("email_not_found")
    }
}