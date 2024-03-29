package com.example.taskertodo.presentation.screens.screen_add_task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.data.repository_impl.TaskRepositoryImpl
import com.example.taskertodo.domain.use_cases.category.GetAllCategoriesUseCaseImpl
import com.example.taskertodo.domain.use_cases.task.AddNewTaskUseCaseImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID

class AddTaskViewModel : ViewModel() {
    private val taskRepository = TaskRepositoryImpl()
    private val addNewTaskUseCase = AddNewTaskUseCaseImpl(taskRepository)
    val getAllCategoriesUseCase = GetAllCategoriesUseCaseImpl(taskRepository)

    var uiState by mutableStateOf(AddTaskUiState())

    init {
        getAllCategoriesUseCase().onEach {
            uiState = uiState.copy(taskCategoryList = it)
            Log.d("AAA","category: $it \n")
        }.launchIn(viewModelScope)
    }

    fun updateTitleText(title: String) {
        uiState = uiState.copy(
            taskText = title
        )
    }

    fun updateDate(date: String) {
        uiState = uiState.copy(
            taskDate = date
        )
    }

    fun updateTime(time: String) {
        uiState = uiState.copy(
            taskTime = time
        )
    }

    fun updateTaskCategory(taskCategoryModel: CategoryModel) {
        uiState = uiState.copy(
            taskCategory = taskCategoryModel
        )
    }

    fun updateTaskCategoryColor(color: String) {
        uiState = uiState.copy(
            taskColor = color
        )
    }

    fun addNewTask() {
        val task = TaskModel(
            taskText = uiState.taskText.toString(),
            taskDate = uiState.taskDate.toString(),
            taskTime = uiState.taskTime.toString(),
            categoryId = uiState.taskCategory?.id.toString(),
            taskId = UUID.randomUUID().toString(),
            categoryColor = uiState.taskColor.toString(),
        )
        addNewTaskUseCase.addNewTask(task)
        uiState = AddTaskUiState()
    }
}