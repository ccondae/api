package org.ccondaeapi.domain.dto

data class QuestionUpload (
        val title: String,
        val githubUrl: String,
        val content: String,
        val code: String,
        val purpose: String,
        var categoryIds: List<Long>,
)