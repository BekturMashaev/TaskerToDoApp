package com.example.taskertodo.domain.use_cases.category

import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCaseImpl(
    private val getAllCategories: TaskRepository
):GetAllCategoriesUseCase {
    override fun invoke(): Flow<List<CategoryModel>> {
        return getAllCategories.getAllCategories()
    }

}