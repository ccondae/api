package org.ccondaeapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "question")
class Question(
        @field: Id
        @field: GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var title: String? = null,
        var text: String? = null,
        var createdAt: LocalDateTime? = null,
        var likeCount: Long = 0,
        var viewCount: Long = 0,

        @field: OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
        var categories: List<QuestionCategory> = listOf(),

        @field: JoinColumn(name = "id")
        @field: OneToMany
        var comments: List<Comment> = listOf()
)