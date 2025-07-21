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
        Mockito.`when`(taskRepo.fetchTasks()).thenReturn(flowOf(emptyList()))
    }

    @Test
    fun `addTask should call insertTasks on repository`() =
        runTest {
            viewModel.onNewTaskTitleChange("Title")
            viewModel.onNewTaskDescriptionChange("Desc")
            viewModel.addTask()
            verify(taskRepo).insertTasks(tasks = Tasks(title = "Title", description = "Desc"))
        }

    @Test
    fun `deleteTask should call deleteTasks on repository`() =
        runTest {
            viewModel.onNewTaskTitleChange("Title")
            viewModel.onNewTaskDescriptionChange("Desc")
            viewModel.deleteTask()
            verify(taskRepo).deleteTasks(tasks = Tasks(title = "Title", description = "Desc"))
        }

    @Test
    fun `updateTask should call updateTasks on repository`() =
        runTest {
            viewModel.onNewTaskTitleChange("Title")
            viewModel.onNewTaskDescriptionChange("Desc")
            viewModel.updateTask()
            verify(taskRepo).updateTasks(tasks = Tasks(title = "Title", description = "Desc"))
        }
}
