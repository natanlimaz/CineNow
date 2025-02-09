package com.devspacecinenow.list.data.remote

import com.devspacecinenow.common.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListService {

    @GET("now_playing?language=pt-BR&page=1")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("top_rated?language=pt-BR&page=1")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @GET("popular?language=pt-BR&page=1")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("upcoming?language=pt-BR&page=1")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

}