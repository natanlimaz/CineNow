package com.devspacecinenow.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.common.data.remote.model.MovieDto
import com.devspacecinenow.detail.data.DetailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val detailService: DetailService
) : ViewModel() {

    private var _uiMovie = MutableStateFlow<MovieDto?>(null);
    val uiMovie: StateFlow<MovieDto?> = _uiMovie;

    fun fetchMovieById(movieId: String) {
        if(_uiMovie.value == null) {
           viewModelScope.launch(Dispatchers.IO) {
               val response = detailService.getMovieById(movieId);
               if(response.isSuccessful) {
                   _uiMovie.value = response.body();
               }
               else {
                   Log.d("MovieDetailViewModel", "Request error :: ${response.errorBody()}")
               }
           }
        }
    }

    fun cleanMovieId() {
        viewModelScope.launch {
            delay(1000);
            _uiMovie.value = null;
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val detailService = RetrofitClient.retrofitInstance.create(DetailService::class.java);
                return MovieDetailViewModel(detailService) as T
            }
        }
    }
}