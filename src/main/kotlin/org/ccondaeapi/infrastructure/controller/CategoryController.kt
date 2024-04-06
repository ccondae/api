package org.ccondaeapi.infrastructure.controller

import org.ccondaeapi.application.CategoryService
import org.ccondaeapi.entity.Category
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/category")
class CategoryController(
        private val categoryService: CategoryService
){
    @GetMapping("/find-all")
    fun findAll(): List<Category> {
        val result: List<Category> = categoryService.findAll()
        return result
    }
}