package org.ccondaeapi.infrastructure.repository

import org.ccondaeapi.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}