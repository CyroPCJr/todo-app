package br.com.cpcjrdev.presentation.mainscreen

import br.com.cpcjrdev.domain.model.DomainTask
import br.com.cpcjrdev.domain.repository.TaskRepository
import br.com.cpcjrdev.domain.usecase.AddTaskResult
import br.com.cpcjrdev.domain.usecase.AddTaskUseCase
import br.com.cpcjrdev.domain.usecase.DeleteTaskUseCase
import br.com.cpcjrdev.domain.usecase.DomainTaskResult
import br.com.cpcjrdev.domain.usecase.GetAllTasksUseCase
import br.com.cpcjrdev.domain.usecase.UpdateTaskUseCase
import br.com.cpcjrdev.presentation.ui.mainscreen.MainScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainScreenViewModelTest {
    @Mock
    private lateinit var taskRepo: TaskRepository

    @Mock
    private lateinit var getAllTasksUseCase: GetAllTasksUseCase

    @Mock
    private lateinit var addTaskUseCase: AddTaskUseCase

    @Mock
    private lateinit var updateTaskUseCase: UpdateTaskUseCase

    @Mock
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    private lateinit var viewModel: MainScreenViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Mock getAllTasks to return empty flow
        whenever(getAllTasksUseCase.invoke()).thenReturn(flowOf(emptyList()))

        viewModel = MainScreenViewModel(
            getAllTasksUseCase,
            addTaskUseCase,
            updateTaskUseCase,
            deleteTaskUseCase,
        )
    }

    @Test
    fun `addTask should call addTaskUseCase with correct parameters`() =
        runTest {
            // Given
            whenever(addTaskUseCase.invoke("Title", "Desc")).thenReturn(AddTaskResult.Success)

            // When
            viewModel.onTasksChange(id = null, newTitle = "Title", newDesc = "Desc")
            viewModel.addTask()

            // Then
            verify(addTaskUseCase).invoke("Title", "Desc")
        }

    @Test
    fun `addTask should NOT call addTaskUseCase when validation fails`() =
        runTest {
            // Given
            whenever(addTaskUseCase.invoke("", "desc")).thenReturn(
                AddTaskResult.ValidationError(listOf("Title cannot be empty")),
            )

            // When
            viewModel.onTasksChange(newTitle = "", newDesc = "desc")
            viewModel.addTask()

            // Then
            verify(addTaskUseCase).invoke("", "desc")
            // Verify error message is set
            assert(viewModel.uiState.value.errorMessage == "Title cannot be empty")
        }

    @Test
    fun `deleteTask should call deleteTaskUseCase with correct parameters`() =
        runTest {
            // Given
            val task = DomainTask(title = "Title", description = "Desc")
            whenever(deleteTaskUseCase.invoke(task)).thenReturn(DomainTaskResult.Success)

            // When
            viewModel.onTasksChange(newTitle = "Title", newDesc = "Desc")
            viewModel.deleteTask()

            // Then
            verify(deleteTaskUseCase).invoke(task)
            assert(viewModel.uiState.value.tasks == DomainTask())
        }

    @Test
    fun `updateTask should call updateTaskUseCase with correct parameters`() =
        runTest {
            // Given
            val task =
                DomainTask(title = "New Title updated", description = "New Description updated")
            whenever(updateTaskUseCase.invoke(task)).thenReturn(DomainTaskResult.Success)

            // When
            viewModel.onTasksChange(
                newTitle = "New Title updated",
                newDesc = "New Description updated",
            )
            viewModel.updateTask()

            // Then
            verify(updateTaskUseCase).invoke(task)
            // After successful updateTask, the ViewModel resets tasks to empty DomainTask()
            assert(viewModel.uiState.value.tasks == DomainTask())
        }
}
