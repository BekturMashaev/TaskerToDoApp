package com.example.taskertodo.domain.use_cases.task

import com.example.taskertodo.data.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface GetAllTaskUseCase {
    operator fun invoke(): Flow<List<TaskModel>>
}