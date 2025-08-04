package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
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
    val dialogState = rememberEditTaskDialogState(
        initialTitle = title,
        initialDescription = description,
    )

    // Animation states
    val dialogScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300),
        label = "edit_dialog_scale",
    )

    val dialogAlpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300),
        label = "edit_dialog_alpha",
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .scale(dialogScale)
            .alpha(dialogAlpha),
        title = { Text(text = stringResource(id = R.string.dialog_edit_title)) },
        text = {
            Column {
                OutlinedTextField(
                    value = dialogState.title,
                    onValueChange = { newTitle ->
                        dialogState.updateTitle(newTitle)
                        onTitleChange(newTitle)
                    },
                    label = { Text(text = stringResource(id = R.string.dialog_label_title)) },
                    supportingText = {
                        if (dialogState.titleError) {
                            Text(text = stringResource(id = R.string.dialog_error_title))
                        }
                    },
                    isError = dialogState.titleError,
                )
                OutlinedTextField(
                    value = dialogState.description,
                    onValueChange = { newDescription ->
                        dialogState.updateDescription(newDescription)
                        onDescriptionChange(newDescription)
                    },
                    label = { Text(text = stringResource(id = R.string.dialog_label_desc)) },
                    supportingText = {
                        if (dialogState.descriptionError) {
                            Text(text = stringResource(id = R.string.dialog_error_description))
                        }
                    },
                    isError = dialogState.descriptionError,
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (dialogState.attemptSubmit()) {
                        onConfirm()
                    }
                },
            ) { Text(text = stringResource(id = R.string.dialog_btn_update)) }
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
