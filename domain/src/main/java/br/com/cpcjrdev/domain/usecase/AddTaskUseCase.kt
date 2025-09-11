package br.com.cpcjrdev.domain.usecase

import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.repository.TaskRepository
import br.com.cpcjrdev.domain.validation.TaskValidator
import br.com.cpcjrdev.domain.validation.ValidationResult
import javax.inject.Inject

sealed class AddTaskResult {
    object Success : AddTaskResult()

    data class ValidationError(
        val errors: List<String>,
    ) : AddTaskResult()

    data class Error(
        val message: String,
    ) : AddTaskResult()
}

class AddTaskUseCase
    @Inject
    constructor(
        private val repository: TaskRepository,
    ) {
        suspend operator fun invoke(
            title: String,
            description: String,
        ): AddTaskResult {
            return try {
                val validationResults = TaskValidator.validateTask(title, description)
                val errors = validationResults.filterIsInstance<ValidationResult.Error>()

                if (errors.isNotEmpty()) {
                    return AddTaskResult.ValidationError(errors.map { it.message })
                }

                val task = DomainTask(
                    title = title.trim(),
                    description = description.trim(),
                )

                repository.addTask(task)
                AddTaskResult.Success
            } catch (e: Exception) {
                AddTaskResult.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
