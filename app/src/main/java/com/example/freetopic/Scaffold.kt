package com.example.freetopic

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.freetopic.appbars.BottomBar
import com.example.freetopic.appbars.ScreenTopBar
import com.example.freetopic.appbars.TopBar
import com.example.freetopic.ui.screens.InfoScreen
import com.example.freetopic.ui.screens.MainScreen


@Composable
fun Scaffold() {
    val navController = rememberNavController() // 创建 NavController
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                "home" -> TopBar(navController)
                "info" -> ScreenTopBar("Info",navController)

            }
        },
        // drawerContent = { DrawerContent(navController) },  // 侧边栏内容
        bottomBar = { BottomBar(navController) },   // 底部导航栏

        content = { innerPadding ->
            val modifier = Modifier
                .padding(innerPadding)
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable(route = "home") { MainScreen(modifier) }
                composable(route = "info") { InfoScreen(modifier) }

            }
        }
    )
}

