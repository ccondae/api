package org.ccondaeapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity(name = "question_category")
class QuestionCategory(
        @field:Id
        @field:ManyToOne
        @field:JoinColumn(name = "question_id")
        var question: Question? = null,
        @field:ManyToOne
        @field:JoinColumn(name = "category_id")
        var category: Category? = null,
)