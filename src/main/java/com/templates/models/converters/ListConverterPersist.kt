package com.templates.models.converters

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.*
import java.util.stream.Collectors

@Converter(autoApply = true)
class ListConverterPersist : AttributeConverter<List<String>, String> {
    companion object {
        const val SPLIT_CHAR = ","
    }

    override fun convertToDatabaseColumn(list: List<String>?): String? {
        if (list.isNullOrEmpty()) {
            return null
        }
        return list.stream().map {
            it.toString()
        }.collect(Collectors.joining(SPLIT_CHAR))
    }

    override fun convertToEntityAttribute(string: String?): List<String> {
        if (string.isNullOrEmpty()) {
            return Collections.emptyList()
        }
        return string.split(SPLIT_CHAR)
    }
}