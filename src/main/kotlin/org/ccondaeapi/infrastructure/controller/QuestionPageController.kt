package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.QuestionService
import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/question")
class QuestionPageController(
        val questionService: QuestionService
) {
    @GetMapping("/not-answered")
    fun notAnsweredQuestionByCategories(
            @RequestParam(required = false, defaultValue = "")
            categories: List<Long>,
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.notAnsweredQuestionByCategories(categories, pageable)
    }

    @GetMapping("/answered")
    fun answeredQuestionByCategories(
            @RequestParam(required = false, defaultValue = "")
            categories: List<Long>,
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.answeredQuestionByCategories(categories, pageable)
    }

    @GetMapping("/popular")
    fun getPopularQuestions(
            @RequestParam(required = false, defaultValue = "")
            categories: List<Long>,

            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int,
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.getPopularContents(categories, pageable)
    }
}