package dev.easysouls.culinarycompass.presentation.recipes

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.easysouls.culinarycompass.R
import dev.easysouls.culinarycompass.domain.recipes.model.Recipe
import dev.easysouls.culinarycompass.presentation.util.RecipesTopAppBar

@Composable
fun RecipesScreen(
    @StringRes userMessage: Int,
    onAddRecipe: () -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    onUserMessageDisplayed: () -> Unit,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: RecipesViewModel = hiltViewModel(),
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            RecipesTopAppBar(
                openDrawer = openDrawer
            )
        },
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddRecipe) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.new_recipe))
            }
        }
    ) { paddingValues ->
        RecipesContent(
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
fun RecipesContent(
    modifier: Modifier = Modifier
) {

}