package com.templates.models.springdata.jpa

import com.templates.models.entities.SenderAttempt
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.*

@Entity
@Table(name = "sender_attempts")
class SenderAttemptJpaEntity {


    @Id
    @Column(name = "id")
    lateinit var id: UUID

    @Column(name = "is_sent")
    var isSent: Boolean = false

    @Column(name = "description_error")
    lateinit var descriptionError: String

    @Column(name = "attempt_moment")
    lateinit var attemptMoment: Instant

    companion object {
        fun from(senderAttempt: SenderAttempt): SenderAttemptJpaEntity {
            return SenderAttemptJpaEntity().apply {
                id = senderAttempt.id
                isSent = senderAttempt.isSent
                descriptionError = senderAttempt.descriptionError
                attemptMoment = senderAttempt.attemptMoment
            }
        }
    }

    fun toDomain(): SenderAttempt {
        return SenderAttempt(
                id = id,
                isSent = isSent,
                descriptionError = descriptionError,
                attemptMoment = attemptMoment
        )
    }
}