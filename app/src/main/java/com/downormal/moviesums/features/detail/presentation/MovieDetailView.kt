package com.downormal.moviesums.features.detail.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.downormal.moviesums.features.detail.domain.model.Review
import com.downormal.moviesums.ui.theme.MoviesUMSTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.minutes

@Composable
fun MovieDetailViewRoot(
    viewModel: MovieDetailViewModel = hiltViewModel<MovieDetailViewModel>(),
    navController: NavController? = null
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val reviews = viewModel.userReviews.collectAsLazyPagingItems()

    MovieDetailView(
        state = state,
        reviews = reviews,
        onBackClick = {
            navController?.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailView(
    state: MovieDetailState,
    reviews: LazyPagingItems<Review>,
    onBackClick: () -> Unit = {}
) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedVideoKey = remember { mutableStateOf<String?>(null) }

    val showReviewsSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    if (!state.isLoading && state.movieDetail != null) {

        if (showReviewsSheet.value) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showReviewsSheet.value = false },
                modifier = Modifier
                    .fillMaxHeight()
                    .windowInsetsPadding(
                        WindowInsets.systemBars
                    )
            ) {
                Box(modifier = Modifier.padding(bottom = 16.dp)) {
                    UserReviews(reviews = reviews)
                }
            }
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = state.movieDetail.originalTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis

                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                        }
                    }

                )
            }
        ) { paddingVal ->


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingVal),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        MovieBackdrop(
                            backdropPath = state.movieDetail.backdropPath,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        MoviePoster(
                            posterPath = state.movieDetail.posterPath,
                            modifier = Modifier
                                .padding(16.dp)
                                .width(115.dp)
                                .height(150.dp)
                        )
                    }
                }
                item {
                    val year = LocalDate.parse(state.movieDetail.releaseDate).year
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${state.movieDetail.title} ",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "($year)",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
                item {
                    val parsedDate = LocalDate.parse(state.movieDetail.releaseDate)
                    val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
                    val formattedDate = parsedDate.format(outputFormatter)

                    val duration = state.movieDetail.runtime.toLong().minutes
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                " • ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$duration",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            TextButton(
                                onClick = {
                                    coroutineScope.launch {
                                        showReviewsSheet.value = true
                                    }
                                },
                            ) {
                                Text(
                                    "See Review",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            state.movieDetail.genres.map { genre ->
                                Text(
                                    text = genre.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }

                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Overview",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = state.movieDetail.overview,
                            style = MaterialTheme.typography.bodyMedium,

                            )
                    }
                }
                item {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Videos",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(state.movieDetail.videos.results.size) { idx ->
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(120.dp)
                                    .background(Color.Gray)
                                    .clickable {
                                        selectedVideoKey.value =
                                            state.movieDetail.videos.results[idx].key
                                        showDialog.value = true
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                VideoThumbnail(
                                    videoKey = state.movieDetail.videos.results[idx].key,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(120.dp)
                                )
                                Icon(
                                    Icons.Default.PlayCircleFilled,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showDialog.value && selectedVideoKey.value != null) {
            VideoDialog(
                videoKey = selectedVideoKey.value!!,
                onDismiss = {
                    showDialog.value = false
                    selectedVideoKey.value = null
                }
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun MovieBackdrop(
    backdropPath: String,
    modifier: Modifier = Modifier
) {
    val backdropUrl = "https://image.tmdb.org/t/p/w500$backdropPath"

    Box(
        modifier = modifier
            .height(200.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backdropUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Movie Backdrop",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )
    }
}


@Composable
fun MoviePoster(
    posterPath: String,
    modifier: Modifier = Modifier
) {
    val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Movie Poster",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
    )

}


@Composable
fun VideoThumbnail(videoKey: String, modifier: Modifier = Modifier) {
    val thumbnailUrl = "https://img.youtube.com/vi/$videoKey/hqdefault.jpg"

    AsyncImage(
        model = thumbnailUrl,
        contentDescription = "YouTube Thumbnail",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}


@Composable
fun VideoDialog(
    videoKey: String,
    onDismiss: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                factory = { context ->
                    YouTubePlayerView(context).apply {
                        enableAutomaticInitialization = false
                    }
                },
                update = { youTubePlayerView ->
                    addLifecycleObserverToYouTubePlayerView(youTubePlayerView, lifecycleOwner)

                    youTubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoKey, 0f)
                        }
                    })
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserReviews(
    reviews: LazyPagingItems<Review>,

    ) {

    val state = reviews.loadState

    when (state.refresh) {

        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            val error = state.refresh as LoadState.Error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Error: ${error.error.message}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { reviews.retry() }) {
                        Text("Retry")
                    }
                }
            }
        }

        else -> {
            if (reviews.itemCount == 0) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No reviews available.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    items(reviews.itemCount) { idx ->
                        reviews[idx]?.let { review ->
                            UserReviewCard(review = review)
                        }
                    }

                    if (reviews.loadState.append is LoadState.Loading) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                        }
                    }

                    if (reviews.loadState.append is LoadState.Error) {
                        item {
                            val appendError = reviews.loadState.append as LoadState.Error
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Failed to load more: ${appendError.error.message}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(onClick = { reviews.retry() }) {
                                    Text("Retry")
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun UserReviewCard(
    review: Review
) {
    val avatarUrl = "https://image.tmdb.org/t/p/w500${review.authorDetails.avatarPath}"

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Author Avatar for ${review.author}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = review.author,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = review.createdAt,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = review.content, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


private fun addLifecycleObserverToYouTubePlayerView(
    youTubePlayerView: YouTubePlayerView,
    lifecycleOwner: LifecycleOwner
) {
    lifecycleOwner.lifecycle.addObserver(youTubePlayerView)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MoviesUMSTheme {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = "2 days ago",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Another action packed movie. He's kicking a lot of butte and getting his butte kicked a lot too. Good movie.")
                }
            }
        }


    }
}