package org.ccondaeapi.application

import org.ccondaeapi.entity.Category
import org.ccondaeapi.infrastructure.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository
){
    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

}