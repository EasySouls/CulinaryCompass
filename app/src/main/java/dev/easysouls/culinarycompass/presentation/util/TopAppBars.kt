@file:OptIn(ExperimentalMaterial3Api::class)

package dev.easysouls.culinarycompass.presentation.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.easysouls.culinarycompass.R
import dev.easysouls.culinarycompass.ui.theme.CulinaryCompassTheme

@Composable
fun HomeTopAppBar(
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.home)) },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = stringResource(id = R.string.open_drawer))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RecipesTopAppBar(
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.recipes_title)) },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = stringResource(id = R.string.open_drawer))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AddEditRecipeTopAppBar(
    @StringRes title: Int,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.menu_back))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun RecipesTopAppBarPreview() {
    CulinaryCompassTheme {
        RecipesTopAppBar { }
    }
}

@Preview
@Composable
fun AddEditRecipeTopAppBar() {
    CulinaryCompassTheme {
        AddEditRecipeTopAppBar(R.string.new_recipe) { }
    }
}