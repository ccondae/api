package org.ccondaeapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "comment")
class Comment(
        @field: Id
        @field: GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var text: String? = null,
        var likeCount: Long = 0,

        @field: JoinColumn(name = "questionId")
        @field: ManyToOne
        var question: Question = Question(),

        var createdAt: LocalDateTime? = null,
)