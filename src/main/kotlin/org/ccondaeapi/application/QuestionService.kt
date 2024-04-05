package org.ccondaeapi.application

import org.ccondaeapi.domain.converter.QuestionConverter
import org.ccondaeapi.entity.Question
import org.ccondaeapi.domain.dto.QuestionSaveDto
import org.ccondaeapi.infrastructure.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
        private val questionRepository: QuestionRepository,
        private val questionConverter: QuestionConverter
) {
    fun save(question: QuestionSaveDto): Question {
        val entity = questionConverter.convertToEntity(question)
        return questionRepository.save(entity)
    }

    fun findById(id: Long): Question {
        return questionRepository.findById(id).orElseThrow { IllegalArgumentException("해당 질문이 존재하지 않습니다.") }
    }
}