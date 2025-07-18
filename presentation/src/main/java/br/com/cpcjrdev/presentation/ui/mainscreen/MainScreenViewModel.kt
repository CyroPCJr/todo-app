package br.com.cpcjrdev.presentation.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.data.repository.TasksDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainScreenViewModel
    @Inject
    constructor(
        private val taskRepo: TasksDataRepository,
    ) : ViewModel() {
        fun fetchTasks() = taskRepo.fetchTasks()

        fun addTask(tasks: Tasks) {
            viewModelScope.launch {
                taskRepo.insertTasks(tasks = tasks)
            }
        }

        fun updateTask(tasks: Tasks) {
            viewModelScope.launch {
                taskRepo.updateTasks(tasks = tasks)
            }
        }

        fun deleteTask(tasks: Tasks) {
            viewModelScope.launch {
                taskRepo.deleteTasks(tasks = tasks)
            }
        }
    }
