package com.devspacecinenow.common.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String
) {
    val posterFullPath: String
        get() = "https://image.tmdb.org/t/p/w500${posterPath}"
}
