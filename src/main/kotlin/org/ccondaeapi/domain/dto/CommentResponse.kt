package org.ccondaeapi.domain.dto

import java.time.LocalDateTime

data class CommentResponse(
        val id: Long,
        val content: String,
        val createdAt: LocalDateTime?,
        val likeCount: Long = 0,
)
