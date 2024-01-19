package com.example.taskertodo.data.local.mappers

import com.example.taskertodo.data.local.models.CategoryModelCache
import com.example.taskertodo.data.local.models.TaskModelCache
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.data.models.TaskModel

fun TaskModel.toCache() = TaskModelCache(
    taskId = taskId,
    taskText = taskText,
    taskDate = taskDate,
    taskTime = taskTime,
    categoryId = categoryId,
    categoryColor = categoryColor
)

fun TaskModelCache.toDataModel() = TaskModel(
    taskId = taskId,
    taskText = taskText,
    taskDate = taskDate,
    taskTime = taskTime,
    categoryId = categoryId,
    categoryColor = categoryColor
)

fun CategoryModel.toCache() = CategoryModelCache(
    id = this.id,
    categoryTitle = this.title,
    color = this.color
)

fun CategoryModelCache.toDataModel() = CategoryModel(
    id = this.id,
    title = this.categoryTitle,
    color = this.color
)