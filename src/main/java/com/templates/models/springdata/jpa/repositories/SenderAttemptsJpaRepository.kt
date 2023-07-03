package com.templates.models.springdata.jpa.repositories

import com.templates.models.springdata.jpa.SenderAttemptJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SenderAttemptsJpaRepository : JpaRepository<SenderAttemptJpaEntity, UUID>{
}