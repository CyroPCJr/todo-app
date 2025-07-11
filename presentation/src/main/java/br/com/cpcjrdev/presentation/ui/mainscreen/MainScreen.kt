package br.com.cpcjrdev.presentation.ui.mainscreen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.cpcjrdev.presentation.ui.listscreen.ListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    @StringRes title: Int,
    onAddItem: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = title))
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItem) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Item",
                )
            }
        },
    ) { it ->
        ListScreen(
            modifier = Modifier.padding(it.calculateTopPadding()),
            taskList = listOf(),
        )
    }
}
