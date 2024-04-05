package org.ccondaeapi.application

import org.ccondaeapi.entity.Question
import org.ccondaeapi.infrastructure.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
        private val questionRepository: QuestionRepository
) {
    fun save(question: Question): Question {
        return questionRepository.save(question)
    }

    fun findById(id: Long): Question {
        return questionRepository.findById(id).orElseThrow { IllegalArgumentException("해당 질문이 존재하지 않습니다.") }
    }
}