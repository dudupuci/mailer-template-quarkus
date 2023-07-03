package com.templates.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.templates.models.commands.ProcessEmailCommand
import io.quarkus.runtime.annotations.RegisterForReflection
import java.util.*

@RegisterForReflection
data class ProcessEmailRequest(
        @get:JsonProperty("id")
        val emailId: UUID
) {
    fun toCommand(): ProcessEmailCommand {
        return ProcessEmailCommand(
                emailId = emailId
        )
    }
}