package com.templates.models.converters

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.io.IOException

@Converter
class MapConverter : AttributeConverter<Map<String, Any>, String> {

    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(map: Map<String, Any>): String {
        return try {
            objectMapper.writeValueAsString(map)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException("Erro ao converter para JSONB", e)
        }
    }

    override fun convertToEntityAttribute(dbData: String): Map<String, Any> {
        return try {
            objectMapper.readValue(dbData, object : TypeReference<Map<String, Any>>() {})
        } catch (e: IOException) {
            throw IllegalArgumentException("Erro ao converter de JSONB", e)
        }
    }
}