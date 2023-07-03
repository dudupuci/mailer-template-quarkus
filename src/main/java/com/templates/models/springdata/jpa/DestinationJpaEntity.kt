package com.templates.models.springdata.jpa

import com.templates.models.converters.ListConverterPersist
import com.templates.models.entities.Destination
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "destinations")
class DestinationJpaEntity {

    @Id
    @Column(name = "id")
    lateinit var id: UUID

    @Convert(converter = ListConverterPersist::class)
    @Column(name = "sent_to")
    var to: List<String> = ArrayList()

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