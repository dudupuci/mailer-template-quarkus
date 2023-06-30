package com.templates.models.usecases

import com.templates.models.commands.CreateEmailCommand
import com.templates.models.entities.Email
import com.templates.models.usecases.base.UseCase

interface CreateEmailUseCase : UseCase<CreateEmailCommand, Email>{
}