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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cpcjrdev.data.model.Tasks
import br.com.cpcjrdev.presentation.ui.theme.TodoAppTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    taskList: List<Tasks>,
) {
    LazyColumn(modifier = modifier) {
        items(taskList) { task ->
            CardInfo(
                title = task.title,
                description = task.description,
            )
        }
    }
}

@Composable
fun CardInfo(
    modifier: Modifier = Modifier,
    title: String = "Sample Todo",
    description: String = "This is a sample todo description",
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
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
            // Left aligned text content
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

            // Right aligned action buttons
            Row {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit",
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                    )
                }
            }
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
