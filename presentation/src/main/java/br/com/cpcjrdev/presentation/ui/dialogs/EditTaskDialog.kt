package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.cpcjrdev.presentation.R
import br.com.cpcjrdev.presentation.ui.theme.TodoAppTheme

@Composable
fun EditTaskDialog(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(id = R.string.dialog_edit_title)) },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = stringResource(id = R.string.dialog_label_title)) },
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text(text = stringResource(id = R.string.dialog_label_desc)) },
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) { Text(text = stringResource(id = R.string.dialog_btn_update)) }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text(text = stringResource(id = R.string.dialog_btn_cancel)) }
        },
    )
}

@Preview
@Composable
private fun EditTaskDialogPreview() {
    TodoAppTheme {
        EditTaskDialog(
            title = "Task Title",
            description = "Task Description",
            onTitleChange = {},
            onDescriptionChange = {},
            onConfirm = {},
            onDismiss = {},
        )
    }
}
