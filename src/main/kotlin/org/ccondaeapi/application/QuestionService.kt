package org.ccondaeapi.application

import org.ccondaeapi.domain.converter.QuestionConverter
import org.ccondaeapi.domain.dto.QuestionDetail
import org.ccondaeapi.domain.dto.QuestionSaveDto
import org.ccondaeapi.entity.Category
import org.ccondaeapi.infrastructure.repository.CategoryRepository
import org.ccondaeapi.infrastructure.repository.QuestionCategoryRepository
import org.ccondaeapi.infrastructure.repository.QuestionRepository
import org.ccondaeapi.infrastructure.repository.ifs.QuestionRepositoryCustom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class QuestionService(
        private val questionRepository: QuestionRepository,
        private val questionConverter: QuestionConverter,
        private val questionCategoryRepository: QuestionCategoryRepository,
        private val categoryRepository: CategoryRepository
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

    fun notAnsweredQuestionByCategories(categories: List<Long>, pageable: Pageable): Page<QuestionDetail> {
        var newCategories: List<Long>
        categories.let { category ->
            newCategories = if(category.isEmpty()) {
                val categories: List<Long> =  categoryRepository.findAll().map { it.id!! }
                categories
            } else {
                category.map { it.toLong() }
            }
        }
        val result = questionRepository.notAnsweredQuestionByCategories(newCategories, pageable)
        return result
    }
}