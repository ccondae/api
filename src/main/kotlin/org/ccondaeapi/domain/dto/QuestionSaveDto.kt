package org.ccondaeapi.domain.dto

data class QuestionSaveDto (
        val title: String,
        val text: String,
        var categoryIds: List<Long>,
)