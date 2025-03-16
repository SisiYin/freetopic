package com.example.freetopic.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetopic.MoviesApi
import com.example.freetopic.model.Movie
import kotlinx.coroutines.launch

sealed interface FreeTopicUiState {
    data class Success(val movies: List<Movie>) : FreeTopicUiState
    object Error : FreeTopicUiState
    object Loading: FreeTopicUiState
}
class MoviesViewModel: ViewModel() {
    var freeTopicUiState: FreeTopicUiState by mutableStateOf(FreeTopicUiState.Loading)
        private set

    var nowPlayingMovies = mutableStateListOf<Movie>()
        private set

//    var upcomingMovies = mutableStateListOf<Movie>()
//        private set

    fun fetchNowPlayingMovies() {
        freeTopicUiState = FreeTopicUiState.Loading
        viewModelScope.launch {
            try {
                val response = MoviesApi.moviesService.getNowPlayingMovies()
                nowPlayingMovies.clear()
                nowPlayingMovies.addAll(response.results)
                freeTopicUiState = FreeTopicUiState.Success(response.results)
            } catch (e: Exception) {
                freeTopicUiState = FreeTopicUiState.Error
            }
        }
    }

//    fun fetchUpcomingMovies() {
//        viewModelScope.launch {
//            try {
//                val response = MoviesApi.moviesService.getUpcomingMovies()
//                upcomingMovies.clear()
//                upcomingMovies.addAll(response.results)
//                Log.d("MoviesViewModel", "Successfully fetched Upcoming Movies: ${response.results.size} items")
//            } catch (e: Exception) {
//                Log.d("ERROR", "Failed to fetch movies: ${e.message}")
//            }
//        }
//    }
}
