package org.ccondaeapi.domain.converter

import org.ccondaeapi.domain.dto.CategoryResponse
import org.ccondaeapi.entity.Category
import org.springframework.stereotype.Component

@Component
class CategoryConverter {
    fun toResponse(category: Category): CategoryResponse {
        return CategoryResponse(
                id = category.id ?: 0,
                name = category.name ?: "알 수 없음",
                count = category.count ?: 0
        )
    }
}