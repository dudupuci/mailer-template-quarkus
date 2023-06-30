package com.templates.models.repositories

import com.templates.models.entities.Email
import java.util.*

interface EmailRepository {
    fun save(email: Email)
    fun findById(id: UUID): Email?
}