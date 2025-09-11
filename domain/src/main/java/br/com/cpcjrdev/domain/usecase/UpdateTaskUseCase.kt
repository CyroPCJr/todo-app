package br.com.cpcjrdev.domain.usecase

import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.repository.TaskRepository
import br.com.cpcjrdev.domain.validation.TaskValidator
import javax.inject.Inject

class UpdateTaskUseCase
    @Inject
    constructor(
        private val repository: TaskRepository,
    ) {
        suspend operator fun invoke(task: DomainTask): DomainTaskResult =
            try {
                TaskValidator.validateTask(task.title, task.description)

                val updatedTask = task.copy(
                    title = task.title.trim(),
                    description = task.description.trim(),
                )

                repository.updateTask(task = updatedTask)
                DomainTaskResult.Success
            } catch (e: Exception) {
                DomainTaskResult.Failure(e.message ?: "Unknown error occurred")
            }
    }
