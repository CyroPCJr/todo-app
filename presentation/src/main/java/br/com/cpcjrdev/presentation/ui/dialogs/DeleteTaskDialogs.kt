package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.cpcjrdev.presentation.R
import br.com.cpcjrdev.presentation.ui.theme.TodoAppTheme

@Composable
fun DeleteTaskDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(id = R.string.dialog_delete_title)) },
        text = { Text(text = stringResource(id = R.string.dialog_delete_text)) },
        confirmButton = {
            Button(onClick = onConfirm) { Text(stringResource(id = R.string.dialog_delete_confirm)) }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text(text = stringResource(id = R.string.dialog_btn_cancel)) }
        },
    )
}

@Preview
@Composable
private fun DeleteTasksDialogPreview() {
    TodoAppTheme {
        DeleteTaskDialog(
            onConfirm = { },
            onDismiss = { },
        )
    }
}
