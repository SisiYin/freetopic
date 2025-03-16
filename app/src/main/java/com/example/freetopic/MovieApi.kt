package com.example.freetopic

import com.example.freetopic.model.MoviesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "54c539f0a2dca863d152652c08d28924"

interface MoviesApi {
    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): MoviesResponse

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(): MoviesResponse


    companion object {

        val moviesService: MoviesApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}