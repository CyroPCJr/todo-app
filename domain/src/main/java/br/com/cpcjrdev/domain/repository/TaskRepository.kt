package br.com.cpcjrdev.domain.repository

import br.com.cpcjrdev.domain.model.DomainTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<DomainTask>>

    suspend fun getTaskById(id: Long): Flow<DomainTask?>

    suspend fun addTask(task: DomainTask)

    suspend fun updateTask(task: DomainTask)

    suspend fun deleteTask(task: DomainTask)
}
