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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.freetopic.R
import com.example.freetopic.ui.components.MovieList
import com.example.freetopic.viewmodel.FreeTopicUiState
import com.example.freetopic.viewmodel.MoviesViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel = viewModel()
) {
    val searchWord = moviesViewModel.searchQuery
    // Fetch movies if not already fetched
    LaunchedEffect(Unit) {
        moviesViewModel.fetchNowPlayingMovies()
    }

    val uiState = moviesViewModel.freeTopicUiState

    val filteredMovies = moviesViewModel.getFilteredMovies()
    val filteredMoviesCount = moviesViewModel.getFilteredMoviesCount()

    // Layout
    Column(modifier = modifier) {

        OutlinedTextField(
            value = searchWord,
            onValueChange = { moviesViewModel.updateSearchQuery(it) },
            placeholder = { Text(stringResource(R.string.search_word)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.search_result_number, filteredMoviesCount),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is FreeTopicUiState.Loading -> LoadingScreen()
            is FreeTopicUiState.Error -> ErrorScreen ()
            is FreeTopicUiState.Success -> {
//                val filteredMovies = uiState.movies.filter { it.title.contains(searchWord, ignoreCase = true) }
                MovieList(movies = filteredMovies)
            }
        }
//
    }
}