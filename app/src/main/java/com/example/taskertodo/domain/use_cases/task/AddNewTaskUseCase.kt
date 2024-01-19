package com.example.taskertodo.domain.use_cases.task

import com.example.taskertodo.data.models.TaskModel

interface AddNewTaskUseCase {
        fun addNewTask(taskModel: TaskModel)
}