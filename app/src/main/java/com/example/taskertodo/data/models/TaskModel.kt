package com.example.taskertodo.data.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import java.io.Serializable

@Immutable
data class TaskModel(
    val taskId:String,
    val taskText:String,
    val taskDate:String,
    val taskTime:String,
    val categoryId: String,
){
    companion object{
        val unknown=TaskModel(
            taskId = String(),
            taskText = String(),
            taskDate = String(),
            taskTime = String(),
            categoryId = "",
        )
    }
}
@Immutable
data class CategoryModel(
    val id:String,
    val title:String,
    val color:String
//    val tasks:ImmutableList<TaskModel>
): Serializable