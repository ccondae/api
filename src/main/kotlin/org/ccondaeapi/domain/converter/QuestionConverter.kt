package org.ccondaeapi.domain.converter

import org.ccondaeapi.domain.dto.CategoryResponse
import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.ccondaeapi.domain.dto.QuestionUpload
import org.ccondaeapi.entity.Question
import org.ccondaeapi.entity.QuestionCategory
import org.ccondaeapi.infrastructure.repository.CategoryRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class QuestionConverter(
        private val categoryRepository: CategoryRepository,
        private val categoryConverter: CategoryConverter
) {
    fun toEntity(dto: QuestionUpload): Question {
        var categories = mutableListOf<QuestionCategory>()
        dto.categoryIds.forEach {
            var category = categoryRepository.findById(it).orElseThrow { IllegalArgumentException("해당 카테고리가 존재하지 않습니다.") }
            var questionCategory = QuestionCategory(category = category)
            categories.add(questionCategory)
        }
        var question: Question = Question(
                title = dto.title,
                githubUrl = dto.githubUrl,
                content = dto.content,
                code = dto.code,
                purpose = dto.purpose,
                categories = categories.toList(),
                createdAt = LocalDateTime.now()
        )
        categories.forEach {
            it.question = question
        }
        return question
    }

    fun toSimpleResponse(question: Question): SimpleQuestionResponse {
        var categories = mutableListOf<CategoryResponse>()
        question.categories.forEach {
            it.category?.let { entity -> categories.add(categoryConverter.toResponse(entity)) }
        }

        var response: SimpleQuestionResponse = SimpleQuestionResponse(
                id = question.id ?: 0,
                title = question.title ?: "",
                content = question.content ?: "",
                createdAt = question.createdAt,
                likeCount = question.likeCount,
                viewCount = question.viewCount,
                categories = categories,
        )

        return response
    }
}