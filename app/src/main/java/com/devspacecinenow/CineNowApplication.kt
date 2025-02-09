package com.devspacecinenow

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.RetrofitClient
import com.devspacecinenow.common.data.local.CineNowDatabase
import com.devspacecinenow.list.data.MovieListRepository
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource

class CineNowApplication : Application() {

    private val db by lazy {
         Room.databaseBuilder(
             applicationContext,
             CineNowDatabase::class.java, "database-cine-now"
         ).build();
    }

    private val listService by lazy {
        RetrofitClient.retrofitInstance.create(ListService::class.java);
    }

    private val localDataSource: MovieListLocalDataSource by lazy {
        MovieListLocalDataSource(db.getMovieDao());
    }

    private val remoteDataSource: MovieListRemoteDataSource by lazy {
        MovieListRemoteDataSource(listService);
    }

    val repository: MovieListRepository by lazy {
        MovieListRepository(
            local = localDataSource,
            remote = remoteDataSource
        );
    }
}