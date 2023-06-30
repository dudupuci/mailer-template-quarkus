package com.templates.models.springdata.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SenderAttemptsJpaRepository : JpaRepository<SenderAttemptJpaEntity, UUID>{
}