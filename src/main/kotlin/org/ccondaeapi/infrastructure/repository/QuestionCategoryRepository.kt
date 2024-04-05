package org.ccondaeapi.infrastructure.repository

import org.ccondaeapi.entity.QuestionCategory
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionCategoryRepository : JpaRepository<QuestionCategory, Long> {
}