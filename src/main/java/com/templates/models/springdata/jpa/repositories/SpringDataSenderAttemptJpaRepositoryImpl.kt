package com.templates.models.springdata.jpa.repositories

import com.templates.models.entities.SenderAttempt
import com.templates.models.repositories.SenderAttemptRepository
import com.templates.models.springdata.jpa.SenderAttemptJpaEntity
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class SpringDataSenderAttemptJpaRepositoryImpl(
        private val jpaRepository: SenderAttemptsJpaRepository
) : SenderAttemptRepository {

    @Transactional
    override fun save(senderAttempt: SenderAttempt) {
        this.jpaRepository.save(SenderAttemptJpaEntity.from(senderAttempt))
    }
}