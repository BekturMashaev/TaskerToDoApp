package com.example.taskertodo.presentation.screens.screen_add_category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.repository_impl.TaskRepositoryImpl
import com.example.taskertodo.domain.use_cases.category.AddNewCategoryUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.UUID

class AddCategoryScreenViewModel : ViewModel() {
    private val repository = TaskRepositoryImpl()
    private val addNewCategoryUseCase = AddNewCategoryUseCaseImpl(repository)

    var uiState = mutableStateOf(AddCategoryUiState())
    fun updateCategoryTitle(title: String) {
        uiState.value = uiState.value.copy(
            categoryTitle = title
        )
    }

    fun updateCategoryColor(color: String) {
        uiState.value = uiState.value.copy(
            categoryColor = color
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun addNewCategory() {
        val category = CategoryModel(
            id = UUID.randomUUID().toString(),
            color = uiState.value.categoryColor.toString(),
            title = uiState.value.categoryTitle.toString()
        )
        addNewCategoryUseCase(categoryModel = category)
        uiState.value.categoryColor = null
        uiState.value.categoryTitle = null
    }
}