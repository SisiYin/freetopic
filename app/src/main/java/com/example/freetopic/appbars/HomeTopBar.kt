package com.example.freetopic.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Home") },
        actions = {
            IconButton(onClick = { /* 搜索逻辑 */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}