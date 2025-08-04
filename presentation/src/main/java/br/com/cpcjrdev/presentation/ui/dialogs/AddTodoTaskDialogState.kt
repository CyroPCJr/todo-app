package br.com.cpcjrdev.presentation.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class AddTodoTaskDialogState {
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var hasAttemptedSubmit by mutableStateOf(false)
        private set

    val titleError by derivedStateOf {
        hasAttemptedSubmit && title.isBlank()
    }

    val descriptionError by derivedStateOf {
        hasAttemptedSubmit && description.isBlank()
    }

    val isValid by derivedStateOf {
        title.isNotBlank() && description.isNotBlank()
    }

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updateDescription(newDescription: String) {
        description = newDescription
    }

    fun attemptSubmit(): Boolean {
        hasAttemptedSubmit = true
        return isValid
    }

    fun reset() {
        title = ""
        description = ""
        hasAttemptedSubmit = false
    }

    fun getTitleAndDescription(): Pair<String, String> = Pair(title, description)
}

@Composable
fun rememberAddTodoTaskDialogState(): AddTodoTaskDialogState = remember { AddTodoTaskDialogState() }
