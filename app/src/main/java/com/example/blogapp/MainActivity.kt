package com.example.blogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import com.example.blogapp.ui.*
import com.example.blogapp.ui.theme.BlogAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogAppTheme {
                BlogAppNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogAppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PocketDrawer(
                onNavigate = { route ->
                    scope.launch {
                        drawerState.close()
                        when (route) {
                            "home" -> navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                            "explore" -> navController.navigate(Screen.Explore.route) {
                                popUpTo(Screen.Home.route)
                            }
                            "pocket" -> navController.navigate(Screen.Pocket.route) {
                                popUpTo(Screen.Home.route)
                            }
                            "settings" -> navController.navigate(Screen.Settings.route) {
                                popUpTo(Screen.Home.route)
                            }
                        }
                    }
                },
                onClose = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    HomeScreen(
                        navController = navController,
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }
                composable(Screen.Explore.route) {
                    ExploreScreen(onMenuClick = { scope.launch { drawerState.open() } })
                }
                composable(Screen.Pocket.route) {
                    PocketScreen(
                        navController = navController,
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }
                composable(Screen.Settings.route) {
                    SettingsScreen(onBackClick = { scope.launch { drawerState.open() } })
                }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("postId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val postId = backStackEntry.arguments?.getString("postId") ?: ""
                    DetailScreen(postId = postId, onBackClick = { navController.popBackStack() })
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Pocket : Screen("pocket")
    object Settings : Screen("settings")
    object Detail : Screen("detail/{postId}") {
        fun createRoute(postId: String) = "detail/$postId"
    }
}
