package org.ccondaeapi.infrastructure.repository.ifs

import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.ccondaeapi.entity.Question
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepositoryCustom {
    fun notAnsweredQuestionByCategories( pageable: Pageable): Page<SimpleQuestionResponse>
    fun getPopularQuestion(pageable: Pageable): Page<SimpleQuestionResponse>
    fun answeredQuestionByCategories(pageable: Pageable): Page<SimpleQuestionResponse>
    fun search(keyword: String): List<Question>
}