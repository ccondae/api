package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.QuestionService
import org.ccondaeapi.domain.dto.QuestionDetailResponse
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
    @PostMapping("/upload")
    fun upload(
            @RequestBody
            request: QuestionUpload
    ): SimpleQuestionResponse {
        return questionService.upload(request)
    }

    @GetMapping("/detail/{id}")
    fun findById(
            @PathVariable
            id: Long
    ): QuestionDetailResponse {
        return questionService.getDetail(id)
    }

    @GetMapping("/search/{keyword}")
    fun findById(
            @PathVariable
            keyword: String
    ): List<SimpleQuestionResponse> {
        return questionService.search(keyword)
    }


    @PatchMapping("/like/{id}")
    fun like(@PathVariable id: Long): QuestionDetailResponse {
        return questionService.like(id)
    }

    @GetMapping("/page/not-answered")
    fun notAnsweredQuestionByCategories(
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.notAnsweredQuestionByCategories( pageable)
    }

    @GetMapping("/page/answered")
    fun answeredQuestionByCategories(
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.answeredQuestionByCategories( pageable)
    }

    @PostMapping("/page/popular")
    fun getPopularQuestions(
            @RequestParam(required = false, defaultValue = "10")
            size: Int,
            @RequestParam(required = false, defaultValue = "0")
            page: Int,
    ): Page<SimpleQuestionResponse> {
        val pageable: Pageable = PageRequest.of(page, size)
        return questionService.getPopularContents(pageable)
    }
}