package io.github.douglasgabriel.application.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class ObjectMapperBuilder {
    private val objectMapper = jacksonObjectMapper()

    fun getMapper(): ObjectMapper = objectMapper.apply {
        propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        dateFormat = StdDateFormat()
    }
}
