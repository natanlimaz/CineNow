package com.devspacecinenow

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("now_playing?language=pt-BR&page=1")
    fun getNowPlayingMovies(): Call<MovieResponse>
}