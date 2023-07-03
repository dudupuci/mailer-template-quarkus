package com.templates.models.springdata.jpa.repositories

import com.templates.models.entities.Email
import com.templates.models.repositories.EmailRepository
import com.templates.models.springdata.jpa.EmailJpaEntity
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.util.*

@ApplicationScoped
class SpringDataEmailJpaRepositoryImpl(
        private val jpaRepository: EmailJpaRepository
) : EmailRepository {

    @Transactional
    override fun save(email: Email) {
        this.jpaRepository.save(EmailJpaEntity.from(email))
    }


    override fun findById(id: UUID): Email? {
        return this.jpaRepository.findById(id).map { it.toDomain() }.orElse(null)
    }
}