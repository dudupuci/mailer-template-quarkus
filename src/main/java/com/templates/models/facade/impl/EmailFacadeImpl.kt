package com.templates.models.facade.impl

import com.templates.models.commands.CreateEmailCommand
import com.templates.models.commands.ProcessEmailCommand
import com.templates.models.entities.Email
import com.templates.models.facade.EmailFacade
import com.templates.models.usecases.impl.CreateEmailUseCaseImpl
import com.templates.models.usecases.impl.FindEmailByIdUseCaseImpl
import com.templates.models.usecases.impl.ProcessEmailUseCaseImpl
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class EmailFacadeImpl(
        private val createEmail: CreateEmailUseCaseImpl,
        private val processEmail: ProcessEmailUseCaseImpl,
        private val findEmail: FindEmailByIdUseCaseImpl,
) : EmailFacade {
    override fun save(input: CreateEmailCommand): Email {
        return this.createEmail.execute(input)
    }

    override fun findById(input: UUID): Email? {
        return this.findEmail.execute(input)
    }

    override fun process(input: ProcessEmailCommand): Email {
        return this.processEmail.execute(input.emailId)
    }
}