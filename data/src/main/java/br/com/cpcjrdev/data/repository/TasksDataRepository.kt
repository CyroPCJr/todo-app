package br.com.cpcjrdev.data.repository

import br.com.cpcjrdev.data.dao.TasksDao
import br.com.cpcjrdev.data.mappers.toTasks
import br.com.cpcjrdev.data.mappers.toTasksEntity
import br.com.cpcjrdev.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksDataRepository(
    private val dao: TasksDao,
) {
    fun fetchTasks(): Flow<List<Tasks>> =
        dao.getAll().map { it ->
            it.map { it.toTasks() }
        }

    suspend fun insertTasks(tasks: Tasks) = dao.insert(tasks.toTasksEntity(tasks.id))

    suspend fun deleteTasks(tasks: Tasks) = dao.delete(tasks.toTasksEntity(tasks.id))

    suspend fun updateTasks(tasks: Tasks) = dao.update(tasks.toTasksEntity(tasks.id))
}
