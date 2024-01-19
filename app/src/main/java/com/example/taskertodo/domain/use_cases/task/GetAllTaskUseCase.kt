package com.example.taskertodo.domain.use_cases.task

import com.example.taskertodo.data.models.TaskModel

interface GetAllTaskUseCase {
    fun getAllTasks():List<TaskModel>
}