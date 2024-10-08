package com.hakancevik.youtubemusicclone.common.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hakancevik.youtubemusicclone.common.theme.AppTheme


@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    val items = listOf(Screen.Home, Screen.Samples, Screen.Explore, Screen.Library)
    val currentRoute = currentRoute(navController)

    NavigationBar(containerColor = Color.Black, modifier = modifier) {
        items.forEach { screen ->
            NavigationBarItem(icon = { screen.icon?.let { Icon(it, contentDescription = null) } },
                label = { Text(screen.resourceId?.let { stringResource(it) } ?: "") },
                selected = currentRoute == screen.route,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray
                ),
                onClick = { navigateToScreen(navController, screen) })
        }
    }
}

private fun navigateToScreen(navController: NavController, screen: Screen) {
    navController.navigate(screen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    AppTheme {
        BottomNavigationBar(navController = rememberNavController(), modifier = Modifier)
    }
}
