package com.templates.models.usecases

import com.templates.models.commands.CreateSenderAttemptCommand
import com.templates.models.entities.SenderAttempt
import com.templates.models.usecases.base.UseCase
import java.util.UUID

interface CreateSenderAttemptUseCase : UseCase<CreateSenderAttemptCommand, SenderAttempt> {
}