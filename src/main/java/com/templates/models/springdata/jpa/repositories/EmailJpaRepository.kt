package com.templates.models.springdata.jpa.repositories

import com.templates.models.springdata.jpa.EmailJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EmailJpaRepository : JpaRepository<EmailJpaEntity, UUID> {
}