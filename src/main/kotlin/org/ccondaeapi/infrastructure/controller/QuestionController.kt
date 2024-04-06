package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.QuestionService
import org.ccondaeapi.domain.dto.SimpleQuestionResponse
import org.ccondaeapi.domain.dto.QuestionUpload
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/question")
class QuestionController(
        private val questionService: QuestionService
) {
    @PostMapping("/save")
    fun save(
            @RequestBody
            request: QuestionUpload
    ): SimpleQuestionResponse {
        return questionService.save(request)
    }

    @GetMapping("/find/{id}")
    fun findById(
            @PathVariable
            id: Long
    ): SimpleQuestionResponse {
        return questionService.findById(id)
    }

    @PostMapping("/not-answered")
    fun notAnsweredQuestionByCategories(
            @RequestBody
            categories: CategoryRequest,
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.notAnsweredQuestionByCategories(categories.categories, pageable)
    }

    data class CategoryRequest(
            val categories: List<Long>
    )
}