package org.ccondaeapi.infrastructure.controller

import org.aspectj.weaver.patterns.TypePatternQuestions.Question
import org.ccondaeapi.application.QuestionService
import org.ccondaeapi.domain.dto.QuestionDetail
import org.ccondaeapi.domain.dto.QuestionSaveDto
import org.ccondaeapi.infrastructure.controller.dto.QuestionPageableRequest
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/question")
class QuestionController(
        private val questionService: QuestionService
) {
    @PostMapping("/save")
    fun save(
            @RequestBody
            request: QuestionSaveDto
    ): QuestionDetail {
        return questionService.save(request)
    }

    @GetMapping("/find/{id}")
    fun findById(
            @PathVariable
            id: Long
    ): QuestionDetail {
        return questionService.findById(id)
    }

    @PostMapping("/not-answered")
    fun notAnsweredQuestionByCategories(
            @RequestBody
            categories: List<Long>,
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int
    ): Page<QuestionDetail> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.notAnsweredQuestionByCategories(categories, pageable)
    }
}