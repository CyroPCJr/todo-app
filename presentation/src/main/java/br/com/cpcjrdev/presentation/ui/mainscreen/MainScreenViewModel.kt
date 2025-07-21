package br.com.cpcjrdev.presentation.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.data.repository.TasksDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainScreenViewModel
    @Inject
    constructor(
        private val taskRepo: TasksDataRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(MainScreenUiState())
        val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                taskRepo.fetchTasks().collect { tasks ->
                    _uiState.update { it.copy(taskList = tasks) }
                }
            }
        }

        fun onShowDialog(show: Boolean) {
            _uiState.update { it.copy(showDialog = show) }
        }

        fun onNewTaskTitleChange(newTitle: String) {
            _uiState.update { it.copy(newTaskTitle = newTitle) }
        }

        fun onNewTaskDescriptionChange(newDesc: String) {
            _uiState.update { it.copy(newTaskDescription = newDesc) }
        }

        fun addTask() {
            val current = _uiState.value
            val task = Tasks(title = current.newTaskTitle, description = current.newTaskDescription)
            viewModelScope.launch {
                taskRepo.insertTasks(tasks = task)
                _uiState.update {
                    it.copy(
                        showDialog = false,
                        newTaskTitle = "",
                        newTaskDescription = "",
                    )
                }
            }
        }

        fun updateTask() {
            val current = _uiState.value
            val task = Tasks(title = current.newTaskTitle, description = current.newTaskDescription)
            viewModelScope.launch {
                taskRepo.updateTasks(tasks = task)
                _uiState.update {
                    it.copy(
                        showDialog = false,
                        newTaskTitle = "",
                        newTaskDescription = "",
                    )
                }
            }
        }

        fun deleteTask() {
            val current = _uiState.value
            val task = Tasks(title = current.newTaskTitle, description = current.newTaskDescription)
            viewModelScope.launch {
                taskRepo.deleteTasks(tasks = task)
                _uiState.update {
                    it.copy(
                        showDialog = false,
                        newTaskTitle = "",
                        newTaskDescription = "",
                    )
                }
            }
        }
    }

data class MainScreenUiState(
    val taskList: List<Tasks> = emptyList(),
    val showDialog: Boolean = false,
    val newTaskTitle: String = "",
    val newTaskDescription: String = "",
)
