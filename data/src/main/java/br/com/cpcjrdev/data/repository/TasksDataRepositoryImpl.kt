package br.com.cpcjrdev.data.repository

import br.com.cpcjrdev.data.dao.TasksDao
import br.com.cpcjrdev.data.mappers.toDomainTask
import br.com.cpcjrdev.data.mappers.toTasksEntity
import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksDataRepositoryImpl
    @Inject
    constructor(
        private val dao: TasksDao,
    ) : TaskRepository {
        override fun getAllTasks(): Flow<List<DomainTask>> =
            dao.getAll().map { listTaskEntity ->
                listTaskEntity.map { it.toDomainTask() }
            }

        override suspend fun getTaskById(id: Long): Flow<DomainTask?> = dao.getById(id).map { it?.toDomainTask() }

        override suspend fun addTask(task: DomainTask) {
            dao.insert(task.toTasksEntity())
        }

        override suspend fun updateTask(task: DomainTask) {
            dao.update(task.toTasksEntity())
        }

        override suspend fun deleteTask(task: DomainTask) {
            dao.delete(task.toTasksEntity())
        }
    }
