package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.CategoryService
import org.ccondaeapi.domain.dto.CategoryResponse
import org.ccondaeapi.entity.Category
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/category")
class CategoryController(
        private val categoryService: CategoryService
) {
    @GetMapping("/find-all")
    fun findAll(): List<CategoryResponse> {
        val result: List<CategoryResponse> = categoryService.findAll()
        return result
    }

    @GetMapping("/search/{name}")
    fun search(@PathVariable name: String): List<CategoryResponse> {
        val result: List<CategoryResponse> = categoryService.search(name)
        return result
    }

    @GetMapping("/popular")
    fun getPopularCategories(): List<CategoryResponse> {
        val result: List<CategoryResponse> = categoryService.getPopularCategories()
        return result
    }
}