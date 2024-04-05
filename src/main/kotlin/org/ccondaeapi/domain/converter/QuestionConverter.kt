package org.ccondaeapi.domain.converter

import org.ccondaeapi.domain.dto.QuestionSaveDto
import org.ccondaeapi.entity.Category
import org.ccondaeapi.entity.Question
import org.ccondaeapi.entity.QuestionCategory
import org.ccondaeapi.infrastructure.repository.CategoryRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class QuestionConverter(
        private val categoryRepository: CategoryRepository
) {
    fun convertToEntity(dto: QuestionSaveDto): Question {
        var categories = mutableListOf<QuestionCategory>()
        dto.categoryIds.forEach {
            var category = categoryRepository.findById(it).orElseThrow { IllegalArgumentException("해당 카테고리가 존재하지 않습니다.") }
            var questionCategory = QuestionCategory(category = category)
            categories.add(questionCategory)
        }
        var question: Question = Question(
                title = dto.title,
                text = dto.text,
                categories = categories.toList(),
                createdAt = LocalDateTime.now()
                )
        return question
    }
}