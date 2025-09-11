package br.com.cpcjrdev.domain.usecase

import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase
    @Inject
    constructor(
        private val repository: TaskRepository,
    ) {
        operator fun invoke(): Flow<List<DomainTask>> = repository.getAllTasks()
    }
