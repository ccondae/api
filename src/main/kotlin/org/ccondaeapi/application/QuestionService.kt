package org.ccondaeapi.application

import org.ccondaeapi.domain.converter.QuestionConverter
import org.ccondaeapi.domain.dto.QuestionDetail
import org.ccondaeapi.domain.dto.QuestionSaveDto
import org.ccondaeapi.infrastructure.repository.QuestionCategoryRepository
import org.ccondaeapi.infrastructure.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
        private val questionRepository: QuestionRepository,
        private val questionConverter: QuestionConverter,
        private val questionCategoryRepository: QuestionCategoryRepository
) {
    fun save(question: QuestionSaveDto): QuestionDetail {
        val entity = questionConverter.convertToEntity(question)
        val saved = questionRepository.save(entity)
        questionCategoryRepository.saveAll(saved.categories)
        val response = questionConverter.toDetailReponse(saved)
        return response
    }

    fun findById(id: Long): QuestionDetail {
        val entity = questionRepository.findById(id).orElseThrow { IllegalArgumentException("해당 질문이 존재하지 않습니다.") }
        val response = questionConverter.toDetailReponse(entity)
        return response
    }
}