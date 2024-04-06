package org.ccondaeapi.infrastructure.controller.dto

data class CommentUploadRequest(
        val content: String,
        val questionId: Long
)
