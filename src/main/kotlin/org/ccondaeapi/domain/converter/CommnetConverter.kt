package org.ccondaeapi.domain.converter

import org.ccondaeapi.domain.dto.CommentResponse
import org.ccondaeapi.entity.Comment
import org.springframework.stereotype.Component

@Component
class CommnetConverter{
    fun toResponse(comment: Comment): CommentResponse {
        return CommentResponse(
                id = comment.id!!,
                content = comment.content?:"",
                createdAt = comment.createdAt,
                likeCount = comment.likeCount
        )
    }

}