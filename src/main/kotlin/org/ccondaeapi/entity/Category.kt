package org.ccondaeapi.entity

import jakarta.persistence.*

@Entity(name = "category")
class Category (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var count: Int? = null,

    @field:OneToMany(mappedBy = "category")
    var questions: List<QuestionCategory> = listOf()
)