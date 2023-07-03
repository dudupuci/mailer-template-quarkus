package com.templates.models.springdata.jpa

import com.templates.models.converters.MapConverter
import com.templates.models.entities.Template
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "templates")
class TemplateJpaEntity {

    @Id
    @Column(name = "id")
    lateinit var id: UUID

    @Column(name = "name")
    lateinit var name: String

    @Convert(converter = MapConverter::class)
    @Column(name = "params")
    var params: Map<String, Any> = mapOf()

    companion object {
        fun from(template: Template): TemplateJpaEntity {
            return TemplateJpaEntity().apply {
                id = UUID.randomUUID()
                name = template.name
                params = template.params
            }
        }
    }

    fun toDomain(): Template {
        return Template(
                name = name,
                params = params
        )
    }
}