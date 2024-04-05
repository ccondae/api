package org.ccondaeapi.infrastructure.repository

import org.ccondaeapi.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}