package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.QuestionService
import org.ccondaeapi.domain.dto.QuestionDetail
import org.ccondaeapi.domain.dto.QuestionSaveDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/question")
class QuestionController(
        private val questionService: QuestionService
){
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
}