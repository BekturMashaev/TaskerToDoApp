package com.example.taskertodo.domain.repository

import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun addNewTask(taskModel: TaskModel)

    fun getAllTasks():List<TaskModel>

    fun addCategory(categoryModel: CategoryModel)
    fun getAllCategories(): Flow<List<CategoryModel>>
}