package com.devspacecinenow.list.data

import android.accounts.NetworkErrorException
import android.graphics.Movie
import com.devspacecinenow.common.model.MovieResponse
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource

class MovieListRepository(
    private val local: MovieListLocalDataSource,
    private val remote: MovieListRemoteDataSource
) {

    suspend fun getNowPlaying(): Result<MovieResponse?> {
        return Result.success(MovieResponse(emptyList()))
//        return try {
//            val response = listService.getNowPlayingMovies();
//            if (response.isSuccessful) {
//                Result.success(response.body())
//            } else {
//                Result.failure(NetworkErrorException(response.message()));
//            }
//        }
//        catch (ex: Exception) {
//            ex.printStackTrace();
//            Result.failure(ex);
//        }
    }

    suspend fun getTopRated(): Result<MovieResponse?> {

        return Result.success(MovieResponse(emptyList()))
//        return try {
//            val response = listService.getTopRatedMovies();
//            if(response.isSuccessful) {
//                Result.success(response.body());
//            }
//            else {
//                Result.failure(NetworkErrorException(response.message()));
//            }
//        }
//        catch (ex: Exception) {
//            ex.printStackTrace();
//            Result.failure(ex);
//        }
    }

    suspend fun getPopular(): Result<MovieResponse?> {

        return Result.success(MovieResponse(emptyList()))
//        return try {
//            val response = listService.getPopularMovies();
//            if(response.isSuccessful) {
//                Result.success(response.body());
//            }
//            else {
//                Result.failure(NetworkErrorException(response.message()));
//            }
//        }
//        catch (ex: Exception) {
//            ex.printStackTrace();
//            Result.failure(ex);
//        }
    }

    suspend fun getUpcoming(): Result<MovieResponse?> {

        return Result.success(MovieResponse(emptyList()))
//        return try {
//            val response = listService.getUpcomingMovies();
//            if(response.isSuccessful) {
//                Result.success(response.body());
//            }
//            else {
//                Result.failure(NetworkErrorException(response.message()));
//            }
//        }
//        catch (ex: Exception) {
//            ex.printStackTrace();
//            Result.failure(ex);
//        }
    }
}