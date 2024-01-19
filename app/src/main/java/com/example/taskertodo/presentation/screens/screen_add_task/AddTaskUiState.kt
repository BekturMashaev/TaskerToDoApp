package com.example.taskertodo.presentation.screens.screen_add_task

import com.example.taskertodo.data.models.CategoryModel

data class AddTaskUiState (
    var taskText:String?= null,
    var taskDate:String?= null,
    var taskTime:String?= null,
    var taskCategory:CategoryModel?=null,
    var taskCategoryList: List<CategoryModel>?= emptyList()
)