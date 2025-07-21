package br.com.cpcjrdev.data.mappers

import br.com.cpcjrdev.data.entities.TasksEntity
import br.com.cpcjrdev.data.model.Tasks

fun Tasks.toTasksEntity(): TasksEntity =
    TasksEntity(
        id = this.id,
        title = this.title,
        description = this.description,
    )

fun TasksEntity.toTasks(): Tasks =
    Tasks(
        id = this.id ?: 0,
        title = this.title,
        description = this.description,
    )
