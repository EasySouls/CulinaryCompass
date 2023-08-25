package dev.easysouls.culinarycompass

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.easysouls.culinarycompass.CulinaryDestinationsArgs.TASK_ID_ARG
import dev.easysouls.culinarycompass.CulinaryDestinationsArgs.TITLE_ARG
import dev.easysouls.culinarycompass.CulinaryDestinationsArgs.USER_MESSAGE_ARG
import dev.easysouls.culinarycompass.CulinaryScreens.ADD_EDIT_TASK_SCREEN
import dev.easysouls.culinarycompass.CulinaryScreens.HOME_SCREEN
import dev.easysouls.culinarycompass.CulinaryScreens.STATISTICS_SCREEN
import dev.easysouls.culinarycompass.CulinaryScreens.TASKS_SCREEN
import dev.easysouls.culinarycompass.CulinaryScreens.TASK_DETAIL_SCREEN

/**
 * Arguments used in [CulinaryDestinations] routes
 */
private object CulinaryScreens {
    const val HOME_SCREEN = "home"
    const val TASKS_SCREEN = "tasks"
    const val STATISTICS_SCREEN = "statistics"
    const val TASK_DETAIL_SCREEN = "task"
    const val ADD_EDIT_TASK_SCREEN = "addEditTask"
}

/**
 * Arguments used in [CulinaryDestinations] routes
 */
object CulinaryDestinationsArgs {
    //TODO Only fakes yet
    const val USER_MESSAGE_ARG = "userMessage"
    const val TASK_ID_ARG = "taskId"
    const val TITLE_ARG = "title"
}

/**
 * Destinations used in [MainActivity]
 */
object CulinaryDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val TASKS_ROUTE = "$TASKS_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val STATISTICS_ROUTE = STATISTICS_SCREEN
    const val TASK_DETAIL_ROUTE = "$TASK_DETAIL_SCREEN/{$TASK_ID_ARG}"
    const val ADD_EDIT_TASK_ROUTE = "$ADD_EDIT_TASK_SCREEN/{$TITLE_ARG}?$TASK_ID_ARG={$TASK_ID_ARG}"
}

/**
 * Models the navigation actions in the app
 */
class CulinaryNavigationActions(private val navController: NavController) {

    fun navigateToHome() {
        navController.navigate(CulinaryDestinations.HOME_ROUTE)
    }

    fun navigateToTasks(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(
            TASKS_SCREEN.let {
                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
            }
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }

    fun navigateToStatistics() {
        navController.navigate(CulinaryDestinations.STATISTICS_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToTaskDetail(taskId: String) {
        navController.navigate("$TASK_DETAIL_SCREEN/$taskId")
    }

    fun navigateToAddEditTask(title: Int, taskId: String?) {
        navController.navigate(
            "$ADD_EDIT_TASK_SCREEN/$title".let {
                if (taskId != null) "$it?$TASK_ID_ARG=$taskId" else it
            }
        )
    }
}