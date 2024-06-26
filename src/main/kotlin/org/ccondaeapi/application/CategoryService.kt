package org.ccondaeapi.application

import jakarta.transaction.Transactional
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
        val responses: List<CategoryResponse> = categoryRepository.findByNameContainingIgnoreCaseOrderByCountDesc(name)
                .map { categoryConverter.toResponse(it) }
        return responses
    }

    @Transactional
    fun increaseCount(categoryIds: List<Long>) {
        var categories = categoryRepository.findAllById(categoryIds)
        categories.forEach { it.count++  }
        categoryRepository.saveAll(categories)
    }

    fun getPopularCategories(): List<CategoryResponse> {
        return categoryRepository.findAllByOrderByCountDesc().map { categoryConverter.toResponse(it) }
    }
}