package org.ccondaeapi.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean

class ObjectMapperConfig {
    @Bean
    fun objectMapper() = ObjectMapper().let {
        val objectMapper = ObjectMapper()

        // JDK 8 이후 클래스 사용 가능
        objectMapper.registerModule(Jdk8Module())

        objectMapper.registerModule(JavaTimeModule())

        // 모르는 Json Value에 대해서는 무시하고 나머지 값만 파싱한다.
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        // 비어있는 Bean을 만들 때
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)

        // 날짜 관련 직렬화 설정 해제
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        // 스네이크 케이스
        // objectMapper.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
    }
}