package com.example.taskertodo.data.repository_impl

import com.example.taskertodo.data.local.mappers.toCache
import com.example.taskertodo.data.local.mappers.toDataModel
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.domain.repository.TaskRepository
import com.example.taskertodo.presentation.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl: TaskRepository {
    private val dao = App.database.getTaskDao()
    override fun addNewTask(taskModel: TaskModel) {
        dao.addNewTask(
            taskModelCache = taskModel.toCache()
        )
    }

    override fun getAllTasks(): Flow<List<TaskModel>> {
        return dao.getAllTasks().map {
            it.map{model->
                model.toDataModel()
            }
        }
    }

    override fun addCategory(categoryModel: CategoryModel) {
        dao.addNewCategory(
            categoryModelCache = categoryModel.toCache()
        )
    }

    override fun getAllCategories(): Flow<List<CategoryModel>> {
        return dao.getAllCategories().map {it->
            it.map {
                it.toDataModel()
            }
        }
    }
}