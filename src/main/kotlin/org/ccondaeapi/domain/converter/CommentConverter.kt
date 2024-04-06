package org.ccondaeapi.domain.converter

import org.ccondaeapi.domain.dto.CommentResponse
import org.ccondaeapi.entity.Comment
import org.ccondaeapi.entity.Question
import org.ccondaeapi.infrastructure.controller.dto.CommentUploadRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CommentConverter{
    fun toResponse(comment: Comment): CommentResponse {
        return CommentResponse(
                id = comment.id!!,
                content = comment.content?:"",
                createdAt = comment.createdAt,
                likeCount = comment.likeCount
        )
    }

    fun toEntity(request: CommentUploadRequest, question: Question) : Comment {
        var entity: Comment = Comment(
                content = request.content,
                question = question,
                createdAt = LocalDateTime.now()
        )
        return entity
    }
}