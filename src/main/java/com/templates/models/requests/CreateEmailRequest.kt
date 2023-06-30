package com.templates.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.templates.models.commands.CreateEmailCommand
import com.templates.models.entities.Destination
import com.templates.models.entities.SenderAttempt
import com.templates.models.entities.Template
import com.templates.models.enums.EmailStatus
import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.Instant
import java.util.*

@RegisterForReflection
data class CreateEmailRequest(
        @get:JsonProperty("destination")
        val destination: DestinationRequest = DestinationRequest(),
        @get:JsonProperty("status")
        val status: EmailStatus = EmailStatus.PENDING,
        @get:JsonProperty("attempts")
        val attempts: SenderAttemptRequest = SenderAttemptRequest(),
        @get:JsonProperty("template")
        val template: TemplateRequest = TemplateRequest()
) {

    init {
        println(this)
    }

    fun toCommand(): CreateEmailCommand {
        return CreateEmailCommand(
                destination = destination.toAggregate(),
                status = status,
                attempts = listOf(attempts.toAggregate()),
                template = template.toAggregate()
        )

    }
}

@RegisterForReflection
data class TemplateRequest(
        val name: String = "",
        val params: Map<String, Any> = mapOf()
) {
    fun toAggregate(): Template {
        return Template(
                name = name,
                params = params
        )
    }
}

@RegisterForReflection
data class DestinationRequest(
        val to: List<String> = listOf()
) {
    fun toAggregate(): Destination {
        return Destination(
                to = to
        )
    }
}


@RegisterForReflection
data class SenderAttemptRequest(
        val id: UUID = UUID.randomUUID(),
        @get:JsonProperty("is_sent")
        val isSent: Boolean = false,
        @get:JsonProperty("description_error")
        val descriptionError: String? = "",
        @get:JsonProperty("attempt_moment")
        val attemptMoment: Instant = Instant.now()
) {
    fun toAggregate(): SenderAttempt {
        return SenderAttempt(
                id = id,
                isSent = isSent,
                descriptionError = descriptionError.let { it } ?: "",
                attemptMoment = attemptMoment
        )
    }
}

