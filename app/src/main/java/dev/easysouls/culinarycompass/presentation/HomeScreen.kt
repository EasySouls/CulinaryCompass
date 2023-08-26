package dev.easysouls.culinarycompass.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.time.LocalDateTime

@Composable
fun HomeScreen() {
    val dateString = LocalDateTime.now().toString()
    val dateFromString = LocalDateTime.parse(dateString)

    Text("Home Screen")
    Text(text = dateFromString.toString())
}