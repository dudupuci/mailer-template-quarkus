/*
 * Copyright (C) PAYCO SERVICOS EIRELI - All Rights Reserved.
 *
 * THE CONTENTS OF THIS PROJECT ARE PROPRIETARY AND CONFIDENTIAL.
 * UNAUTHORIZED COPYING, TRANSFERRING OR REPRODUCTION OF THE CONTENTS OF THIS PROJECT, VIA ANY MEDIUM IS STRICTLY PROHIBITED.
 *
 * The receipt or possession of the source code and/or any parts thereof does not convey or imply any right to use them
 * for any purpose other than the purpose for which they were provided to you.
 *
 * The software is provided "AS IS", without warranty of any kind, express or implied, including but not limited to
 * the warranties of merchantability, fitness for a particular purpose and non infringement.
 * In no event shall the authors or copyright holders be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software
 * or the use or other dealings in the software.
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 */

package com.templates.models.entities

import com.templates.models.base.Entity
import com.templates.models.commands.CreateEmailCommand
import com.templates.models.commands.CreateSenderAttemptCommand
import com.templates.models.enums.EmailStatus
import java.time.Instant
import java.util.*


class Email(
        id: UUID = UUID.randomUUID(),
        createdAt: Instant = Instant.now(),
        updatedAt: Instant = Instant.now(),

        val destination: Destination,
        var status: EmailStatus,
        val attempts: List<SenderAttempt>,
        val template: Template,

        ) : Entity(id, createdAt, updatedAt) {

    companion object {
        fun from(command: CreateEmailCommand): Email {
            return Email(
                    destination = command.destination,
                    status = EmailStatus.PENDING,
                    attempts = command.attempts,
                    template = command.template
            )
        }
    }

    fun setToSent() {
        status = EmailStatus.SENT
    }

    fun setToFailed() {
        status = EmailStatus.FAILED
    }
}

data class Template(
        val name: String,
        val params: Map<String, Any>
) {

}

data class Destination(
        val to: List<String>
) {

}

data class SenderAttempt(
        val id: UUID,
        val isSent: Boolean,
        val descriptionError: String,
        val attemptMoment: Instant
) {
    companion object {
        fun from(input: CreateSenderAttemptCommand): SenderAttempt {
            return SenderAttempt(
                    id = UUID.randomUUID(),
                    isSent = input.isSent,
                    descriptionError = input.descriptionError,
                    attemptMoment = input.attemptMoment
            )
        }
    }

}
