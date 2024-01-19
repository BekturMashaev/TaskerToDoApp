package com.example.taskertodo.presentation.screens.screen_main

import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.domain.use_cases.category.GetAllCategoriesUseCase
import com.example.taskertodo.presentation.screens.screen_add_task.AddTaskUiState

data class MainScreenUiState(
    val tasks:List<TaskModel> ?= emptyList(),
    val taskCategory:List<Pair<TaskModel,Int>> = emptyList(),
    val selectCategory:Set<TaskModel> = emptySet(),
    val categories:List<CategoryModel>?= emptyList()
)