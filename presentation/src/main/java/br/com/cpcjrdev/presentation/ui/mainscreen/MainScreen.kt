package br.com.cpcjrdev.presentation.ui.mainscreen

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.cpcjrdev.domain.model.DomainTask
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
        onDismiss = { viewModel.onHideDialog() },
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
            var isPressed by remember { mutableStateOf(false) }
            val scale by animateFloatAsState(
                targetValue = if (isPressed) 0.9f else 1f,
                animationSpec = tween(durationMillis = 100),
                label = "fab_scale",
            )

            FloatingActionButton(
                onClick = {
                    isPressed = true
                    viewModel.onShowDialog()
                    isPressed = false
                },
                modifier = Modifier.scale(scale),
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
            uiState = uiState,
            callbacks = callbacks,
        )
    }
}

data class MainScreenCallbacks(
    val onTasksChange: (Long, String, String) -> Unit = { _, _, _ -> },
    val onDismiss: () -> Unit = {},
    val onConfirm: () -> Unit = {},
    val onEdit: () -> Unit = {},
    val onDelete: () -> Unit = {},
)

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState,
    callbacks: MainScreenCallbacks,
) {
    ListScreen(
        modifier = modifier,
        taskList = uiState.taskList,
        onTasksChange = callbacks.onTasksChange,
        onEditClick = callbacks.onEdit,
        onDeleteClick = callbacks.onDelete,
    )

    if (uiState.showDialog) {
        AddTodoTaskDialog(
            onTasksChange = callbacks.onTasksChange,
            onDismiss = callbacks.onDismiss,
            onConfirm = callbacks.onConfirm,
        )
    }
}

@Preview
@Composable
private fun MainScreeContentPreview() {
    val mockTaskList = listOf(
        DomainTask(id = 1, title = "Buy groceries", description = "Milk, Bread, Eggs"),
        DomainTask(id = 2, title = "Call Alice", description = "Wish her happy birthday"),
        DomainTask(id = 3, title = "Read a book", description = "Finish reading current book"),
    )

    val uiState = MainScreenUiState(
        taskList = mockTaskList,
        showDialog = false,
    )

    TodoAppTheme {
        MainScreenContent(
            modifier = Modifier,
            uiState = uiState,
            callbacks = MainScreenCallbacks(),
        )
    }
}
