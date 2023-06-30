package com.templates.models.usecases

import com.templates.models.entities.Email
import com.templates.models.usecases.base.UseCase
import java.util.*

interface FindEmailByIdUseCase : UseCase<UUID, Email?> {
}