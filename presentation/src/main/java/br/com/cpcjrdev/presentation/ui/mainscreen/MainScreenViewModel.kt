package br.com.cpcjrdev.presentation.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.usecase.AddTaskResult
import br.com.cpcjrdev.domain.usecase.AddTaskUseCase
import br.com.cpcjrdev.domain.usecase.DeleteTaskUseCase
import br.com.cpcjrdev.domain.usecase.DomainTaskResult
import br.com.cpcjrdev.domain.usecase.GetAllTasksUseCase
import br.com.cpcjrdev.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
    @Inject
    constructor(
        private val getAllTasks: GetAllTasksUseCase,
        private val addTask: AddTaskUseCase,
        private val updateTask: UpdateTaskUseCase,
        private val deleteTask: DeleteTaskUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(MainScreenUiState())
        val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

        init {
            loadTasks()
        }

        private fun loadTasks() {
            getAllTasks()
                .onEach { tasks ->
                    _uiState.update {
                        it.copy(
                            taskList = tasks,
                        )
                    }
                }.launchIn(viewModelScope)
        }

        fun onShowDialog() {
            _uiState.update { it.copy(showDialog = true) }
        }

        fun onHideDialog() {
            _uiState.update { it.copy(showDialog = false) }
        }

        fun onTasksChange(
            id: Long = 0,
            newTitle: String,
            newDesc: String,
        ) {
            _uiState.update { it.copy(tasks = DomainTask(id = id, title = newTitle, description = newDesc)) }
        }

        fun addTask() {
            viewModelScope.launch {
                when (
                    val result = addTask(
                        title = _uiState.value.tasks.title,
                        description = _uiState.value.tasks.description,
                    )
                ) {
                    is AddTaskResult.Success -> {
                        _uiState.update {
                            it.copy(
                                showDialog = false,
                                tasks = DomainTask(),
                            )
                        }
                    }

                    is AddTaskResult.ValidationError -> {
                        _uiState.update { it.copy(errorMessage = result.errors.joinToString(", ")) }
                    }

                    is AddTaskResult.Error -> {
                        _uiState.update { it.copy(errorMessage = result.message) }
                    }
                }
            }
        }

        fun updateTask() {
            viewModelScope.launch {
//                updateTask(
//                    task = _uiState.value.tasks,
//                )
//                _uiState.update {
//                    it.copy(
//                        showDialog = false,
//                        tasks = DomainTask(),
//                    )
//                }

                when (val result = updateTask(task = _uiState.value.tasks)) {
                    is DomainTaskResult.Success -> {
                        _uiState.update { it.copy(showDialog = false, tasks = DomainTask()) }
                    }

                    is DomainTaskResult.Failure -> {
                        _uiState.update { it.copy(errorMessage = result.error) }
                    }
                }
            }
        }

        fun deleteTask() {
            viewModelScope.launch {
                when (
                    val result = deleteTask(
                        task = _uiState.value.tasks,
                    )
                ) {
                    is DomainTaskResult.Success -> {
                        _uiState.update {
                            it.copy(
                                showDialog = false,
                                tasks = DomainTask(),
                            )
                        }
                    }

                    is DomainTaskResult.Failure -> {
                        _uiState.update {
                            it.copy(
                                showDialog = false,
                                tasks = DomainTask(),
                                errorMessage = result.error,
                            )
                        }
                    }
                }
            }
        }
    }

data class MainScreenUiState(
    val taskList: List<DomainTask> = emptyList(),
    val showDialog: Boolean = false,
    val errorMessage: String? = null,
    val tasks: DomainTask = DomainTask(),
)
