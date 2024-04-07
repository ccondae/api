package org.ccondaeapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "question")
class Question(
        @field: Id
        @field: GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var title: String? = null,
        var content: String? = null,
        var purpose: String? = null,
        var code: String? = null,
        @field: Column(name = "github_url")
        var githubUrl: String? = null,
        var createdAt: LocalDateTime? = null,
        var likeCount: Long = 0,
        var viewCount: Long = 0,

        @field: OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
        var categories: Set<QuestionCategory> = setOf<QuestionCategory>(),

        @field: OneToMany(mappedBy ="question")
        var comments: Set<Comment> = setOf<Comment>()
)