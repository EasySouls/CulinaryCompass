package dev.easysouls.culinarycompass

import android.app.Activity
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.easysouls.culinarycompass.CulinaryDestinationsArgs.USER_MESSAGE_ARG
import dev.easysouls.culinarycompass.presentation.HomeScreen
import dev.easysouls.culinarycompass.presentation.recipes.RecipesScreen
import dev.easysouls.culinarycompass.presentation.util.AppModalDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CulinaryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    startDestination: String = CulinaryDestinations.HOME_ROUTE,
    navActions: CulinaryNavigationActions = remember(navController) {
        CulinaryNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            CulinaryDestinations.HOME_ROUTE
        ) {
            AppModalDrawer(
                drawerState = drawerState,
                currentRoute = currentRoute,
                navigateActions = navActions
            ) {
                HomeScreen(
                    snackbarHostState = snackbarHostState,
                    openDrawer = { coroutineScope.launch { drawerState.open() }}
                )
            }
        }
        composable(
            CulinaryDestinations.RECIPES_ROUTE,
            arguments = listOf(
                navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
            )
        ) { entry ->
            AppModalDrawer(drawerState, currentRoute, navActions) {
                RecipesScreen(
                    snackbarHostState = snackbarHostState,
                    userMessage = entry.arguments?.getInt(USER_MESSAGE_ARG)!!,
                    onUserMessageDisplayed = { entry.arguments?.putInt(USER_MESSAGE_ARG, 0) },
                    onAddRecipe = { navActions.navigateToAddEditRecipe(R.string.add_task, null) },
                    onRecipeClick = { recipe -> navActions.navigateToRecipeDetail(recipe.mealId) },
                    openDrawer = { coroutineScope.launch { drawerState.open() } }
                )
            }
        }

    }
}


// Keys for navigation
const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3