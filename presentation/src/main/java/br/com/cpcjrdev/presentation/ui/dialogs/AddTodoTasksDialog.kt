package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.cpcjrdev.presentation.R

@Composable
fun AddTodoTaskDialog(
    title: String = "",
    onTitleChange: (String) -> Unit = {},
    description: String = "",
    onDescriptionChange: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(id = R.string.dialog_btn_add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.dialog_btn_cancel))
            }
        },
        title = { Text(stringResource(id = R.string.dialog_title)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = stringResource(id = R.string.dialog_add_label_title)) },
                    singleLine = true,
                )

                TextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text(text = stringResource(id = R.string.dialog_add_label_desc)) },
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
