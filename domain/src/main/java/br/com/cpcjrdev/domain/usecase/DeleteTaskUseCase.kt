package br.com.cpcjrdev.domain.usecase

import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase
    @Inject
    constructor(
        private val repository: TaskRepository,
    ) {
        suspend operator fun invoke(task: DomainTask): DomainTaskResult =
            try {
                repository.deleteTask(task = task)
                DomainTaskResult.Success
            } catch (e: Exception) {
                DomainTaskResult.Failure(error = e.message ?: "Unknown error occurred")
            }
    }
