package com.downormal.moviesums.features.genres.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.downormal.moviesums.app.Route
import kotlin.random.Random

@Composable
fun GenresViewRoot(
    viewModel: GenresViewModel = hiltViewModel<GenresViewModel>(),
    navController: NavController? = null
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GenresView(
        state = state,
        onEvent = viewModel::onEvent,
        onSubmit = { genres ->
           navController?.navigate(Route.Movies(genres))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenresView(
    state: GenresState,
    onEvent: (GenresEvent) -> Unit,
    onSubmit: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Genres",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            )
        },
        bottomBar = {
            if (state.selectedGenreIds.isNotEmpty()) {
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(
                            text = "Genres: ${state.selectedGenreName.joinToString(", ")}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(0.5f)
                        )
                        Button(
                            modifier = Modifier.width(100.dp),
                            onClick = {
                                val selectedGenres = state.selectedGenreIds.joinToString(",")
                                onSubmit(selectedGenres)
                            }
                        ) {
                            Text("Next")
                        }
                    }
                }
            }
        }
    ) { paddingVal ->

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 9.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(paddingVal)
        ) {
            items(state.genre.size) { idx ->
                val genre = state.genre[idx]
                val isSelected = genre.id in state.selectedGenreIds
                val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary
                else remember { getRandomColor() }
                val randomHeight = remember { (100..150).random().dp }
                GenreCard(
                    name = genre.name,
                    backgroundColor = backgroundColor,
                    height = randomHeight,
                    onClick = {
                        onEvent(
                            GenresEvent.OnSelectedGenres(
                                genreId = genre.id,
                                genreName = genre.name
                            )
                        )
                    }
                )
            }
        }
    }


}

@Composable
private fun GenreCard(
    name: String,
    backgroundColor: Color,
    height: Dp = 100.dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            text = name,
            color = Color.White,
            modifier = Modifier.padding(end = 5.dp, bottom = 5.dp)
        )
    }
}


fun getRandomColor(): Color {
    val range = 0f..0.6f
    return Color(
        red = Random.nextFloat().coerceIn(range),
        green = Random.nextFloat().coerceIn(range),
        blue = Random.nextFloat().coerceIn(range),
        alpha = 1f
    )
}