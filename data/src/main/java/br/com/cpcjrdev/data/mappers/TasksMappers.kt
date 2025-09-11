package br.com.cpcjrdev.data.mappers

import br.com.cpcjrdev.data.entities.TasksEntity
import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.domain.model.DomainTask

fun Tasks.toTasksEntity(): TasksEntity =
    TasksEntity(
        id = this.id!!,
        title = this.title,
        description = this.description,
    )

fun TasksEntity.toTasks(): Tasks =
    Tasks(
        id = this.id,
        title = this.title,
        description = this.description,
    )

fun TasksEntity.toDomainTask(): DomainTask = DomainTask(id = this.id, title = this.title, description = this.description)

fun DomainTask.toTasksEntity(): TasksEntity =
    TasksEntity(
        id = this.id,
        title = this.title,
        description = this.description,
    )
