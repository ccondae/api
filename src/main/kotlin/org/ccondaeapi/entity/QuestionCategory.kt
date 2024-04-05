package org.ccondaeapi.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity(name = "question_category")
class QuestionCategory(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:ManyToOne
        @field:JoinColumn(name = "question_id")
        @JsonIgnore
        var question: Question? = null,
        @field:ManyToOne
        @field:JoinColumn(name = "category_id")
        @JsonIgnore
        var category: Category? = null,
)