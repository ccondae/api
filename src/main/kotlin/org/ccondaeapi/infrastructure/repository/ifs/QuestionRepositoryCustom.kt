package org.ccondaeapi.infrastructure.repository.ifs

import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepositoryCustom {
    fun notAnsweredQuestionByCategories(categories: List<Long>, pageable: Pageable): Page<SimpleQuestionResponse>
    fun getPopularQuestion(pageable: Pageable): Page<SimpleQuestionResponse>

}