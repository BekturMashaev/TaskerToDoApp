package com.example.taskertodo.domain.use_cases.category

import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.domain.repository.TaskRepository

class AddNewCategoryUseCaseImpl(
    private val addNewRepository: TaskRepository
):AddNewCategoryUseCase {
    override fun invoke(categoryModel: CategoryModel) {
        addNewRepository.addCategory(categoryModel=categoryModel)
    }
}