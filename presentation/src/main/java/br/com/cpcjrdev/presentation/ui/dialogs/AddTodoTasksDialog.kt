package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
    onDismiss: () -> Unit = {},
    onConfirm: (String, String) -> Unit = { _, _ -> },
) {
    var titleState by remember { mutableStateOf("") }
    var descState by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onConfirm(titleState, descState) }) {
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
                    value = titleState,
                    onValueChange = { titleState = it },
                    label = { Text(text = stringResource(id = R.string.dialog_add_label_title)) },
                    singleLine = true,
                )

                TextField(
                    value = descState,
                    onValueChange = { descState = it },
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
