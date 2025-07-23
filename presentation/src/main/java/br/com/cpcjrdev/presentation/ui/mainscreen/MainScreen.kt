package br.com.cpcjrdev.presentation.ui.mainscreen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.presentation.R
import br.com.cpcjrdev.presentation.ui.dialogs.AddTodoTaskDialog
import br.com.cpcjrdev.presentation.ui.listscreen.ListScreen
import br.com.cpcjrdev.presentation.ui.theme.TodoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    @StringRes title: Int,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val viewModel: MainScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val callbacks = MainScreenCallbacks(
        onTasksChange = viewModel::onTasksChange,
        onDismiss = { viewModel.onShowDialog(show = false) },
        onConfirm = viewModel::addTask,
        onEdit = viewModel::updateTask,
        onDelete = viewModel::deleteTask,
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = title))
            }, scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onShowDialog(show = true) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.fab_add_item),
                )
            }
        },
    ) { it ->
        MainScreenContent(
            modifier = Modifier.padding(it),
            taskList = uiState.taskList,
            showDialog = uiState.showDialog,
            title = uiState.newTaskTitle,
            description = uiState.newTaskDescription,
            callbacks = callbacks,
        )
    }
}

data class MainScreenCallbacks(
    val onTasksChange: (Long?, String, String) -> Unit,
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
    val onEdit: () -> Unit = {},
    val onDelete: () -> Unit = {},
)

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    taskList: List<Tasks>,
    showDialog: Boolean,
    title: String,
    description: String,
    callbacks: MainScreenCallbacks,
) {
    ListScreen(
        modifier = modifier,
        taskList = taskList,
        onTasksChange = callbacks.onTasksChange,
        onEditClick = callbacks.onEdit,
        onDeleteClick = callbacks.onDelete,
    )

    if (showDialog) {
        AddTodoTaskDialog(
            title = title,
            onTasksChange = callbacks.onTasksChange,
            description = description,
            onDismiss = callbacks.onDismiss,
            onConfirm = callbacks.onConfirm,
        )
    }
}

@Preview
@Composable
private fun MainScreeContentPreview() {
    val mockTaskList = listOf(
        Tasks(id = 1, title = "Buy groceries", description = "Milk, Bread, Eggs"),
        Tasks(id = 2, title = "Call Alice", description = "Wish her happy birthday"),
        Tasks(id = 3, title = "Read a book", description = "Finish reading current book"),
    )
    TodoAppTheme {
        MainScreenContent(
            modifier = Modifier,
            taskList = mockTaskList,
            showDialog = false,
            title = "",
            description = "",
            callbacks = MainScreenCallbacks(
                onTasksChange = { _, _, _ -> },
                onDismiss = {},
                onConfirm = {},
            ),
        )
    }
}
