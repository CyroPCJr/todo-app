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
            if (it.isNotEmpty()) {
                it.map { it.toTasks() }
            } else {
                emptyList()
            }
        }

    suspend fun insertTasks(tasks: Tasks) = dao.insert(tasks.toTasksEntity())

    suspend fun deleteTasks(tasks: Tasks) = dao.delete(tasks.toTasksEntity())

    suspend fun updateTasks(tasks: Tasks) = dao.update(tasks.toTasksEntity())
}
