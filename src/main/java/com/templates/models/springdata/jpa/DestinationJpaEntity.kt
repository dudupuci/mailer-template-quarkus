package com.templates.models.springdata.jpa

import com.templates.models.entities.Destination
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Transient
import java.util.*

@Entity
@Table(name = "destinations")
class DestinationJpaEntity {

    @Id
    @Column(name = "id")
    lateinit var id: UUID

    @Transient
    lateinit var to: List<String>

    companion object {
        fun from(destination: Destination): DestinationJpaEntity {
            return DestinationJpaEntity().apply {
                id = UUID.randomUUID()
                to = destination.to
            }
        }
    }

    fun toDomain(): Destination {
        return Destination(
                to = to
        )
    }

}