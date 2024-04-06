package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.CommentService
import org.ccondaeapi.domain.dto.CommentResponse
import org.ccondaeapi.infrastructure.controller.dto.CommentUploadRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comment")
class CommentController(
        private val commentService: CommentService
) {
    @PostMapping("/upload")
    fun upload(@RequestBody request: CommentUploadRequest): CommentResponse {
        return commentService.upload(request)
    }

    @GetMapping("/{type}/{id}")
    fun editLike(
            @PathVariable type: CommentService.PointType,
            @PathVariable id: Long
    ): CommentResponse {
        return commentService.editLikeCount(type, id)
    }
}