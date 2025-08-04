package br.com.cpcjrdev.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TasksEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val title: String,
    val description: String,
)
