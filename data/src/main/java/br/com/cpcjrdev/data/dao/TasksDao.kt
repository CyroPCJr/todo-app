package br.com.cpcjrdev.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.cpcjrdev.data.entities.TasksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Insert
    suspend fun insert(tasks: TasksEntity)

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TasksEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: Long): TasksEntity?

    @Update
    suspend fun update(tasks: TasksEntity): Int

    @Delete
    suspend fun delete(tasks: TasksEntity): Int
}
