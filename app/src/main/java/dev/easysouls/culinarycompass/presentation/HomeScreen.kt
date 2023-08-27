package dev.easysouls.culinarycompass.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.easysouls.culinarycompass.presentation.util.HomeTopAppBar
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val dateString = LocalDateTime.now().toString()
    val dateFromString = LocalDateTime.parse(dateString)
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            HomeTopAppBar(
                openDrawer = openDrawer
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            Text("Home Screen")
            Text(text = dateFromString.toString())
        }
    }
}