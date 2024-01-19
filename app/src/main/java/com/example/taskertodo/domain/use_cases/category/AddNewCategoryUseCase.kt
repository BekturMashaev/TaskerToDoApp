package com.example.taskertodo.domain.use_cases.category

import com.example.taskertodo.data.models.CategoryModel

interface AddNewCategoryUseCase {
    operator fun invoke(categoryModel: CategoryModel)
}