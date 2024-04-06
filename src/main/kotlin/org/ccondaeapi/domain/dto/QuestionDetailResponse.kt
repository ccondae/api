package org.ccondaeapi.domain.dto

import java.time.LocalDateTime

data class QuestionDetailResponse(
        val id: Long,
        val title: String,
        val githubUrl: String,
        val content: String,
        val code: String,
        val purpose: String,
        val likeCount: Long,
        val createdAt: LocalDateTime?,
        val categories: List<CategoryResponse>,
        val comments: List<CommentResponse>
)
