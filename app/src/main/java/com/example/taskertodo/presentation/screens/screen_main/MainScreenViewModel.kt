package com.example.taskertodo.presentation.screens.screen_main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.data.repository_impl.TaskRepositoryImpl
import com.example.taskertodo.domain.repository.TaskRepository
import com.example.taskertodo.domain.use_cases.category.GetAllCategoriesUseCaseImpl
import com.example.taskertodo.domain.use_cases.task.AddNewTaskUseCaseImpl
import com.example.taskertodo.domain.use_cases.task.GetAllTaskUseCaseImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainScreenViewModel : ViewModel() {
    private val repository: TaskRepository = TaskRepositoryImpl()
    var uiState by mutableStateOf(MainScreenUiState())
    val getAllCategoriesUseCase=GetAllCategoriesUseCaseImpl(repository)
    val getAllTaskUseCaseImpl=GetAllTaskUseCaseImpl(repository)

    init {
        getAllCategoriesUseCase().onEach {
            uiState=uiState.copy(categories = it)
        }.launchIn(viewModelScope)
    }
    init {
        getAllTaskUseCaseImpl().onEach {
            uiState=uiState.copy(tasks = it)
        }.launchIn(viewModelScope)
    }
}