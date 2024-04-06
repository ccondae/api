package org.ccondaeapi.infrastructure.repository

import org.ccondaeapi.entity.Question
import org.ccondaeapi.infrastructure.repository.ifs.QuestionRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long>, QuestionRepositoryCustom {
    fun findByTitleContainingIgnoreCase(title: String): List<Question>
    fun findByCodeContainingIgnoreCase(code: String): List<Question>
    fun findByContentContainingIgnoreCase(content: String): List<Question>
    fun findByPurposeContainingIgnoreCase(purpose: String): List<Question>
}