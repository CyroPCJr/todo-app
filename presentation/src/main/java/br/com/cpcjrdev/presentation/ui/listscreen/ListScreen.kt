package br.com.cpcjrdev.presentation.ui.listscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.presentation.ui.dialogs.DeleteTaskDialog
import br.com.cpcjrdev.presentation.ui.dialogs.EditTaskDialog
import br.com.cpcjrdev.presentation.ui.theme.TodoAppTheme

private enum class DialogMode { Edit, Delete }

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    taskList: List<Tasks>,
    onTasksChange: (Long?, String, String) -> Unit = { _, _, _ -> },
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(taskList) { task ->
            CardInfo(
                title = task.title,
                description = task.description,
                onTasksChange = onTasksChange,
                id = task.id!!,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick,
            )
        }
    }
}

@Composable
fun CardInfo(
    modifier: Modifier = Modifier,
    title: String = "Sample Todo",
    description: String = "This is a sample todo description",
    onTasksChange: (Long?, String, String) -> Unit = { _, _, _ -> },
    id: Long = 0,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogMode by remember { mutableStateOf<DialogMode?>(null) }
    var editableTitle by remember { mutableStateOf(title) }
    var editableDescription by remember { mutableStateOf(description) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }

            Row {
                IconButton(onClick = {
                    dialogMode = DialogMode.Edit
                    editableTitle = title
                    editableDescription = description
                    showDialog = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit task",
                    )
                }
                IconButton(onClick = {
                    dialogMode = DialogMode.Delete
                    showDialog = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete task",
                    )
                }
            }
        }
    }

    if (showDialog) {
        when (dialogMode) {
            DialogMode.Edit -> {
                EditTaskDialog(
                    title = editableTitle,
                    description = editableDescription,
                    onTitleChange = { editableTitle = it },
                    onDescriptionChange = { editableDescription = it },
                    onConfirm = {
                        onTasksChange(id, editableTitle, editableDescription)
                        onEditClick()
                        showDialog = false
                    },
                    onDismiss = { showDialog = false },
                )
            }
            DialogMode.Delete -> {
                DeleteTaskDialog(
                    onConfirm = {
                        onTasksChange(id, "", "")
                        onDeleteClick()
                        showDialog = false
                    },
                    onDismiss = { showDialog = false },
                )
            }
            else -> {}
        }
    }
}

@Preview()
@Composable
fun CardInfoPreview() {
    TodoAppTheme {
        CardInfo()
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    TodoAppTheme {
        ListScreen(
            taskList = listOf(
                Tasks(id = 0, title = "Task 1", description = "Description 1"),
                Tasks(id = 1, title = "Task 2", description = "Description 2"),
                Tasks(
                    id = 2,
                    title = "Task 2",
                    description = "Very long description to test the text overflow and ellipsis feature bla bla bla",
                ),
            ),
        )
    }
}
