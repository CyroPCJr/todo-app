package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.cpcjrdev.presentation.R

@Composable
fun AddTodoTaskDialog(
    onTasksChange: (Long, String, String) -> Unit = { _, _, _ -> },
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    val dialogState = rememberAddTodoTaskDialogState()

    // Animation states
    val dialogScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300),
        label = "dialog_scale",
    )

    val dialogAlpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300),
        label = "dialog_alpha",
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .scale(dialogScale)
            .alpha(dialogAlpha),
        confirmButton = {
            Button(onClick = {
                if (dialogState.attemptSubmit()) {
                    val (title, description) = dialogState.getTitleAndDescription()
                    onTasksChange(0, title, description)
                    dialogState.reset()
                    onConfirm()
                }
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
                    value = dialogState.title,
                    onValueChange = dialogState::updateTitle,
                    label = { Text(text = stringResource(id = R.string.dialog_label_title)) },
                    singleLine = true,
                    supportingText = {
                        if (dialogState.titleError) {
                            Text(text = stringResource(id = R.string.dialog_error_title))
                        }
                    },
                    isError = dialogState.titleError,
                )

                OutlinedTextField(
                    value = dialogState.description,
                    onValueChange = dialogState::updateDescription,
                    label = { Text(text = stringResource(id = R.string.dialog_label_desc)) },
                    maxLines = 2,
                    supportingText = {
                        if (dialogState.descriptionError) {
                            Text(text = stringResource(id = R.string.dialog_error_description))
                        }
                    },
                    isError = dialogState.descriptionError,
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
