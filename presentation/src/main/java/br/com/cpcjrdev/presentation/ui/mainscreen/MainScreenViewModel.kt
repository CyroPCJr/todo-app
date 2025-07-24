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

        fun onTasksChange(
            id: Long? = null,
            newTitle: String,
            newDesc: String,
        ) {
            _uiState.update { it.copy(tasks = Tasks(id = id, title = newTitle, description = newDesc)) }
        }

        fun addTask() {
            if (_uiState.value.tasks.title
                    .isBlank() || _uiState.value.tasks.description
                    .isBlank()
            ) {
                return
            }
            viewModelScope.launch {
                taskRepo.insertTasks(tasks = _uiState.value.tasks)
                _uiState.update {
                    it.copy(
                        showDialog = false,
                        tasks = Tasks(),
                    )
                }
            }
        }

        fun updateTask() {
            viewModelScope.launch {
                taskRepo.updateTasks(tasks = _uiState.value.tasks)
                _uiState.update {
                    it.copy(
                        showDialog = false,
                        tasks = Tasks(),
                    )
                }
            }
        }

        fun deleteTask() {
            viewModelScope.launch {
                taskRepo.deleteTasks(tasks = _uiState.value.tasks)
                _uiState.update {
                    it.copy(
                        showDialog = false,
                        tasks = Tasks(),
                    )
                }
            }
        }
    }

data class MainScreenUiState(
    val taskList: List<Tasks> = emptyList(),
    val showDialog: Boolean = false,
    val editingTaskId: Long? = null,
    val newTaskTitle: String = "",
    val titleErrorMessage: String = "Title should not be empty",
    val newTaskDescription: String = "",
    val descriptionErrorMessage: String = "Description should not be empty",
    val tasks: Tasks = Tasks(),
)
