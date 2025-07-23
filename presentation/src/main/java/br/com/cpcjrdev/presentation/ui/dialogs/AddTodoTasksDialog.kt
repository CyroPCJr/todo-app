package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.cpcjrdev.presentation.R

@Composable
fun AddTodoTaskDialog(
    title: String = "",
    description: String = "",
    onTasksChange: (Long?, String, String) -> Unit = { _, _, _ -> },
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    var titleChange by remember { mutableStateOf(title) }
    var descChange by remember { mutableStateOf(description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onTasksChange(null, titleChange, descChange)
                onConfirm()
            }) {
                Text(text = stringResource(id = R.string.dialog_btn_add))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.dialog_btn_cancel))
            }
        },
        title = { Text(stringResource(id = R.string.dialog_add_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    value = titleChange,
                    onValueChange = { titleChange = it },
                    label = { Text(text = stringResource(id = R.string.dialog_label_title)) },
                    singleLine = true,
                )

                OutlinedTextField(
                    value = descChange,
                    onValueChange = { descChange = it },
                    label = { Text(text = stringResource(id = R.string.dialog_label_desc)) },
                    maxLines = 2,
                )
            }
        },
    )
}

@Preview
@Composable
fun AddTodoTaskDialogPreview() {
    AddTodoTaskDialog()
}
