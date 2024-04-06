package org.ccondaeapi.application

import org.ccondaeapi.domain.converter.CommentConverter
import org.ccondaeapi.domain.dto.CommentResponse
import org.ccondaeapi.entity.Comment
import org.ccondaeapi.entity.Question
import org.ccondaeapi.infrastructure.controller.dto.CommentUploadRequest
import org.ccondaeapi.infrastructure.repository.CommentRepository
import org.ccondaeapi.infrastructure.repository.QuestionRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class CommentService(
        private val commentRepository: CommentRepository,
        private val commentConverter: CommentConverter,
        private val questionRepository: QuestionRepository
) {
    fun upload(request: CommentUploadRequest): CommentResponse {
        val question: Question = questionRepository.findById(request.questionId).orElseThrow {
            IllegalArgumentException("존재하지 않는 Question 입니다.")
        }
        val entity = commentConverter.toEntity(request, question)
        val savedEntity = commentRepository.save(entity)
        val response: CommentResponse = commentConverter.toResponse(savedEntity)
        return response
    }

    fun editLikeCount(
            type: PointType,
            commentId: Long
    ): CommentResponse {
        val searchedEntity: Comment = commentRepository.findById(commentId).orElseThrow {
            IllegalArgumentException("존재하지 않는 댓글 입니다.")
        }

        if (type == PointType.LIKE) {
            searchedEntity.likeCount++
        } else {
            searchedEntity.likeCount--
        }
        val savedEntity = commentRepository.save(searchedEntity)
        val response = commentConverter.toResponse(savedEntity)
        return response
    }

    enum class PointType {
        LIKE, DISLIKE
    }
}