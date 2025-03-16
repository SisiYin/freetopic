package com.example.freetopic.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetopic.api.MoviesApi
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
    var searchQuery by mutableStateOf("") // 搜索关键词
    private set

    // 更新搜索查询并触发过滤
    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    // 获取过滤后的电影列表
    fun getFilteredMovies(): List<Movie> {
        return nowPlayingMovies.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    // 获取过滤后的电影数量
    fun getFilteredMoviesCount(): Int {
        return getFilteredMovies().size
    }

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
}
