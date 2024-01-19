package com.example.taskertodo.domain.use_cases.category

import com.example.taskertodo.data.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface GetAllCategoriesUseCase {
    operator fun invoke():Flow<List<CategoryModel>>
}