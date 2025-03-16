package com.example.freetopic.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.freetopic.MovieList
import com.example.freetopic.viewmodel.FreeTopicUiState
import com.example.freetopic.viewmodel.MoviesViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel = viewModel()
) {
    var searchWord by remember { mutableStateOf("") }
    // Fetch movies if not already fetched
    LaunchedEffect(Unit) {
        moviesViewModel.fetchNowPlayingMovies()
        //moviesViewModel.fetchUpcomingMovies()
    }
//
//    val filteredMovies = moviesViewModel.nowPlayingMovies.filter {
//        it.title.contains(searchWord, ignoreCase = true)
//    }
    val uiState = moviesViewModel.freeTopicUiState


    // Layout
    Column(modifier = modifier) {

        OutlinedTextField(
            value = searchWord,
            onValueChange = { searchWord = it },
            placeholder = { Text("Search Movies") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Show Now Playing Movies
        //Text("Now Playing Movies", style = MaterialTheme.typography.titleLarge)
//        MovieList(movies = filteredMovies)
        when (uiState) {
            is FreeTopicUiState.Loading -> LoadingScreen()  // 显示加载动画
            is FreeTopicUiState.Error -> ErrorScreen () // 显示错误 + Retry 按钮
            is FreeTopicUiState.Success -> {
                val filteredMovies = uiState.movies.filter { it.title.contains(searchWord, ignoreCase = true) }
                MovieList(movies = filteredMovies)  // 显示电影列表
            }
        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Show Upcoming Movies
//        Text("Upcoming Movies", style = MaterialTheme.typography.titleLarge)
//        MovieList(movies = moviesViewModel.upcomingMovies)
    }
}