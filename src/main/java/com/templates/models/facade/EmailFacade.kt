package com.templates.models.facade

import com.templates.models.commands.CreateEmailCommand
import com.templates.models.entities.Email
import java.util.*

interface EmailFacade {
    fun save(input: CreateEmailCommand): Email
    fun findById(input: UUID): Email?
    fun processEmail(input: UUID): Email
}