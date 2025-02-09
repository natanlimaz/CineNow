package com.devspacecinenow.list.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspacecinenow.common.model.MovieDto
import com.devspacecinenow.list.presentation.MovieListViewModel

@Composable
fun MovieListScreen(
    navController: NavHostController,
    viewModel: MovieListViewModel
) {
    val nowPlayingMovies by viewModel.uiNowPlaying.collectAsState()
    val topRatedMovies by viewModel.uiTopRated.collectAsState();
    val popularMovies by viewModel.uiPopular.collectAsState();
    val upcomingMovies by viewModel.uiUpcoming.collectAsState();

    MovieListContent(
        topRated = topRatedMovies,
        nowPlaying = nowPlayingMovies,
        popular = popularMovies,
        upcoming = upcomingMovies,
    ) { itemClicked ->
        navController.navigate(route = "movieDetail/${itemClicked.id}")
    }
}

@Composable
private fun MovieListContent(
    topRated: MovieListUiState,
    nowPlaying: MovieListUiState,
    popular: MovieListUiState,
    upcoming: MovieListUiState,
    onClick: (MovieUiData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            text = "CineNow"
        )

        MovieSession(
            movieListUiState = topRated,
            label = "Top rated",
            onClick = onClick
        )

        MovieSession(
            movieListUiState = nowPlaying,
            label = "Now playing",
            onClick = onClick
        )

        MovieSession(
            movieListUiState = popular,
            label = "Popular",
            onClick = onClick
        )

        MovieSession(
            movieListUiState = upcoming,
            label = "Upcoming",
            onClick = onClick
        )
    }
}


@Composable
private fun MovieSession(
    label: String,
    movieListUiState: MovieListUiState,
    onClick: (MovieUiData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = label,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))

        if(movieListUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
        else if(movieListUiState.isError) {
            Text(
                color = Color.Red,
                text = movieListUiState.errorMessage ?: "",
            )
        }
        else {
            MovieList(
                movieListUiState = movieListUiState.list,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MovieList(
    movieListUiState: List<MovieUiData>,
    onClick: (MovieUiData) -> Unit
) {
    LazyRow {
        items(movieListUiState) {
            MovieItem(
                movieDto = it,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MovieItem(
    movieDto: MovieUiData,
    onClick: (MovieUiData) -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clickable {
                onClick.invoke(movieDto)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(120.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop,
            model = movieDto.image,
            contentDescription = "${movieDto.title} Poster image"
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = movieDto.title
        )
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = movieDto.overview
        )
    }
}