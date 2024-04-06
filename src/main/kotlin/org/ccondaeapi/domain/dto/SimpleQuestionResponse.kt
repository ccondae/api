package org.ccondaeapi.domain.dto

import java.time.LocalDateTime

data class SimpleQuestionResponse(
        val id: Long,
        val title: String,
        val content: String,
        val createdAt: LocalDateTime?,
        val likeCount: Long = 0,
        val viewCount: Long = 0,
        val commentCount: Long = 0,
        val categories: List<CategoryResponse>
)
