package org.ccondaeapi.application

import org.ccondaeapi.domain.converter.CategoryConverter
import org.ccondaeapi.domain.dto.CategoryResponse
import org.ccondaeapi.infrastructure.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val categoryConverter: CategoryConverter
){
    fun findAll(): List<CategoryResponse> {
        return categoryRepository.findAll().map { categoryConverter.toResponse(it) }
    }

    fun search(name: String): List<CategoryResponse> {
        val responses: List<CategoryResponse> = categoryRepository.findByNameContainingIgnoreCase(name)
                .map { categoryConverter.toResponse(it) }
        return responses
    }

}