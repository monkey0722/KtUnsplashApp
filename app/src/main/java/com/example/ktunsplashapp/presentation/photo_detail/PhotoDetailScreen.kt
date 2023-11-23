package com.example.ktunsplashapp.presentation.photo_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ktunsplashapp.domain.models.PhotoDetail
import com.example.ktunsplashapp.presentation.components.CountLabel
import com.example.ktunsplashapp.presentation.ui.theme.Dimensions

@Composable
fun PhotoDetailScreen(viewModel: PhotoDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            !state.error.isNullOrBlank() -> {
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                )
            }
            else -> {
                state.photoDetail?.let { photoDetail ->
                    PhotoDetailContent(photoDetail = photoDetail)
                }
            }
        }
    }
}

@Composable
fun PhotoDetailContent(photoDetail: PhotoDetail) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        LoadingImage(photoDetail.imageUrl, photoDetail.description)

        Column(modifier = Modifier.padding(Dimensions.Space16)) {
            Text(
                text = photoDetail.description ?: "No description",
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(Dimensions.Space20))

            Text(
                text = photoDetail.photographer ?: "Unknown photographer",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(Dimensions.Space20))

            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = photoDetail.likes ?: 0,
                iconTint = Color.Magenta,
                contentDescription = "likes",
            )
            CountLabel(
                imageVector = Icons.Default.Share,
                count = photoDetail.downloads ?: 0,
                iconTint = Color.Green,
                contentDescription = "Share",
            )
            Spacer(modifier = Modifier.height(Dimensions.Space20))

            Text(text = "Camera: ${photoDetail.camera}")
            Text(text = "Location: ${photoDetail.location}")
        }
    }
}

@Composable
fun LoadingImage(
    imageUrl: String,
    contentDescription: String?,
) {
    Box(modifier = Modifier.heightIn(min = Dimensions.Space200)) {
        var isLoadingImage by remember { mutableStateOf(true) }
        if (isLoadingImage) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomEndPercent = 5,
                            bottomStartPercent = 5,
                        ),
                    ),
            onSuccess = { isLoadingImage = false },
        )
    }
}
