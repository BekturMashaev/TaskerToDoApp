package com.example.taskertodo.domain.use_cases.task

import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.domain.repository.TaskRepository

class GetAllTaskUseCaseImpl(
    private val getAllTasksTaskRepository: TaskRepository
): GetAllTaskUseCase {
    override fun getAllTasks(): List<TaskModel> {
        return getAllTasksTaskRepository.getAllTasks()
    }

}