package org.ccondaeapi.infrastructure.repository

import org.ccondaeapi.entity.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long>{

}