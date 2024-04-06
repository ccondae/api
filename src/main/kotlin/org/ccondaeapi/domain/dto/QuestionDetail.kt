package org.ccondaeapi.domain.dto

import org.ccondaeapi.entity.Category
import java.time.LocalDateTime

data class QuestionDetail(
        val id: Long,
        val title: String,
        val text: String,
        val createdAt: LocalDateTime?,
        val categories: List<CategoryResponse>
)
