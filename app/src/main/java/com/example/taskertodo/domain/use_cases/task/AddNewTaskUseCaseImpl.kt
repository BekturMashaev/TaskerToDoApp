package com.example.taskertodo.domain.use_cases.task

import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.domain.repository.TaskRepository

class AddNewTaskUseCaseImpl(
    private val getAllTasksTaskRepository: TaskRepository
) : AddNewTaskUseCase {
    override fun addNewTask(taskModel: TaskModel) {
        getAllTasksTaskRepository.addNewTask(
            taskModel=taskModel
        )
    }
}