package br.com.cpcjrdev.presentation.mainscreen

import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.data.repository.TasksDataRepository
import br.com.cpcjrdev.presentation.ui.mainscreen.MainScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainScreenViewModelTest {
    @Mock
    private lateinit var taskRepo: TasksDataRepository

    private lateinit var viewModel: MainScreenViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainScreenViewModel(taskRepo)
    }

    @Test
    fun `addTask should call insertTasks on repository`() =
        runTest {
            val task = Tasks(id = 1L, title = "Title", description = "Desc")
            viewModel.addTask(task)
            verify(taskRepo).insertTasks(tasks = task)
        }

    @Test
    fun `deleteTask should call deleteTasks on repository`() =
        runTest {
            val task = Tasks(id = 1L, title = "Title", description = "Desc")
            viewModel.deleteTask(task)
            verify(taskRepo).deleteTasks(tasks = task)
        }

    @Test
    fun `updateTask should call updateTasks on repository`() =
        runTest {
            val task = Tasks(id = 1L, title = "Title1", description = "Desc1")
            viewModel.updateTask(tasks = task)
            verify(taskRepo).updateTasks(tasks = task)
        }
}
