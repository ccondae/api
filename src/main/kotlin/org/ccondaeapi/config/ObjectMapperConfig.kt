package org.ccondaeapi.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import javax.xml.catalog.Catalog



class ObjectMapperConfig {
    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().let {
        val objectMapper = ObjectMapper()

        objectMapper.registerModule(Jdk8Module())
        /**
         *
         */
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        // 스네이크 케이스
        // objectMapper.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
    }
}