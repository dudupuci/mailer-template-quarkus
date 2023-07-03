package com.templates.models.usecases.impl

import com.templates.models.entities.Email
import com.templates.models.enums.EmailStatus
import com.templates.models.repositories.EmailRepository
import com.templates.models.usecases.ProcessEmailUseCase
import io.quarkus.mailer.Mail
import io.quarkus.mailer.Mailer
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class ProcessEmailUseCaseImpl(
        private val repository: EmailRepository,
        private val mailer: Mailer
) : ProcessEmailUseCase {
    override fun execute(input: UUID): Email {
        val email = this.repository.findById(input)
                ?: throw RuntimeException("email_not_found")

        if (email.status == EmailStatus.PENDING) {
            try {
                mailer.send(Mail.withText(
                        listOf(email.destination.to).toString(),
                        "Send test",
                        "Sending a simple mail"
                ))
                email.setToSent()
            } catch (err: Exception) {
                email.setToFailed()
                throw RuntimeException("Error: " + err.message)
            } finally {
                email.setUpdatedAt()
                repository.save(email)
            }
        }

        return email
    }
}