package com.example.taskertodo.domain.use_cases.task

import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetAllTaskUseCaseImpl(
    private val getAllTasksTaskRepository: TaskRepository
): GetAllTaskUseCase {
    override fun invoke(): Flow<List<TaskModel>> {
        return getAllTasksTaskRepository.getAllTasks()
    }
}