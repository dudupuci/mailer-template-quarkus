package com.templates.models.springdata.jpa

import com.templates.models.entities.Email
import com.templates.models.enums.EmailStatus
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "emails")
class EmailJpaEntity {

    @Id
    @Column(name = "id")
    lateinit var id: UUID

    @Column(name = "created_at")
    lateinit var createdAt: Instant

    @Column(name = "updated_at")
    lateinit var updatedAt: Instant

    @JoinColumn(name = "destination_id")
    @OneToOne(cascade = [CascadeType.ALL], targetEntity = DestinationJpaEntity::class)
    lateinit var destination: DestinationJpaEntity

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    lateinit var status: EmailStatus

    @ElementCollection
    @CollectionTable(
            name = "email_sender_attempts",
            joinColumns = [JoinColumn(name = "email_id")],
            schema = "public"
    )
    lateinit var attempts: List<SenderAttemptJpaEntity>

    @JoinColumn(name = "template_id")
    @OneToOne(cascade = [CascadeType.ALL], targetEntity = TemplateJpaEntity::class)
    lateinit var template: TemplateJpaEntity

    companion object {
        fun from(email: Email): EmailJpaEntity {
            return EmailJpaEntity().apply {
                id = email.id
                createdAt = email.createdAt
                updatedAt = email.updatedAt
                destination = DestinationJpaEntity.from(email.destination)
                status = email.status
                attempts = email.attempts.map { SenderAttemptJpaEntity.from(it) }
                template = TemplateJpaEntity.from(email.template)
            }
        }
    }

    fun toDomain(): Email {
        return Email(
                id = id,
                createdAt = createdAt,
                updatedAt = updatedAt,
                destination = destination.toDomain(),
                status = status,
                attempts = attempts.map { it.toDomain() }.toMutableList(),
                template = template.toDomain()
        )
    }
}