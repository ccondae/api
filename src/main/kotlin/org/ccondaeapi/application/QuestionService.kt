package org.ccondaeapi.application

import jakarta.transaction.Transactional
import org.ccondaeapi.domain.converter.QuestionConverter
import org.ccondaeapi.domain.dto.QuestionDetailResponse
import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.ccondaeapi.domain.dto.QuestionUpload
import org.ccondaeapi.entity.Question
import org.ccondaeapi.infrastructure.repository.QuestionCategoryRepository
import org.ccondaeapi.infrastructure.repository.QuestionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@Transactional
class QuestionService(
        private val questionRepository: QuestionRepository,
        private val questionConverter: QuestionConverter,
        private val questionCategoryRepository: QuestionCategoryRepository,
        private val categoryService: CategoryService
) {
    fun upload(question: QuestionUpload): SimpleQuestionResponse {
        // Entity 저장
        val entity = questionConverter.toEntity(question)
        val saved = questionRepository.save(entity)

        // QuestionCategory 연관 설정
        questionCategoryRepository.saveAll(saved.categories)

        // Category들 Count 1 증가
        categoryService.increaseCount(question.categoryIds)

        val response = questionConverter.toSimpleResponse(saved)
        return response
    }

    fun like(id: Long): QuestionDetailResponse {
        val searched: Question = questionRepository.findById(id).orElseThrow {
            IllegalArgumentException("찾고자 하는 질문이 없습니다.")
        }
        searched.likeCount++
        val saved: Question = questionRepository.save(searched)
        val response: QuestionDetailResponse = questionConverter.toDetailResponse(saved)
        return response
    }

    fun getDetail(id: Long): QuestionDetailResponse {
        var entity = questionRepository.findById(id).orElseThrow { IllegalArgumentException("해당 질문이 존재하지 않습니다.") }
        entity = increaseViewCount(entity)
        val response = questionConverter.toDetailResponse(entity)
        return response
    }

    fun getPopularContents(pageable: Pageable): Page<SimpleQuestionResponse> {
        return questionRepository.getPopularQuestion(pageable)
    }

    private fun increaseViewCount(entity: Question): Question {
        entity.viewCount++
        return questionRepository.save(entity)
    }

    fun notAnsweredQuestionByCategories(pageable: Pageable): Page<SimpleQuestionResponse> {
        val result = questionRepository.notAnsweredQuestionByCategories(pageable)
        return result
    }

    fun answeredQuestionByCategories(pageable: Pageable): Page<SimpleQuestionResponse> {
        val result = questionRepository.answeredQuestionByCategories(pageable)
        return result
    }

    fun search(keyword: String): List<SimpleQuestionResponse> {
        val searchResult = questionRepository.search(keyword)
        val result = searchResult.map { questionConverter.toSimpleResponse(it) }
        return result
    }
}