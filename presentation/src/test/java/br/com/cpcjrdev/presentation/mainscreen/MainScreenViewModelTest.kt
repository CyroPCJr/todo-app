package br.com.cpcjrdev.presentation.mainscreen

import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.data.repository.TasksDataRepository
import br.com.cpcjrdev.presentation.ui.mainscreen.MainScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainScreenViewModelTest {
    @Mock
    private lateinit var taskRepo: TasksDataRepository

    private lateinit var viewModel: MainScreenViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(taskRepo.fetchTasks()).thenReturn(flowOf(emptyList()))
        viewModel = MainScreenViewModel(taskRepo)
    }

    @Test
    fun `addTask should call insertTasks on repository`() =
        runTest {
            viewModel.onTasksChange(id = null, newTitle = "Title", newDesc = "Desc")
            viewModel.addTask()
            verify(taskRepo).insertTasks(tasks = Tasks(title = "Title", description = "Desc"))
        }

    @Test
    fun `addTask should NOT call insertTasks when title is blank`() =
        runTest {
            viewModel.onTasksChange(id = null, newTitle = "", newDesc = "desc")
            viewModel.addTask()
            verify(taskRepo, Mockito.never()).insertTasks(any())
        }

    @Test
    fun `addTask should NOT call insertTasks when description is blank`() =
        runTest {
            viewModel.onTasksChange(id = null, newTitle = "title", newDesc = "")
            viewModel.addTask()
            verify(taskRepo, Mockito.never()).insertTasks(any())
        }

    @Test
    fun `deleteTask should call deleteTasks on repository`() =
        runTest {
            viewModel.onTasksChange(id = null, newTitle = "Title", newDesc = "Desc")
            viewModel.addTask()
            viewModel.deleteTask()
            verify(taskRepo).deleteTasks(tasks = Tasks(title = "Title", description = "Desc"))
            assert(viewModel.uiState.value.tasks == Tasks())
        }

    @Test
    fun `updateTask should call updateTasks on repository`() =
        runTest {
            viewModel.onTasksChange(id = null, newTitle = "Title", newDesc = "Desc")
            viewModel.addTask()
            viewModel.onTasksChange(id = null, newTitle = "New Title updated", newDesc = "New Description updated")
            viewModel.updateTask()
            verify(taskRepo).updateTasks(tasks = Tasks(title = "New Title updated", description = "New Description updated"))
            assert(viewModel.uiState.value.tasks == Tasks())
        }
}
