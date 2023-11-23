package com.example.ktunsplashapp.presentation.search_photos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.ktunsplashapp.domain.models.Photo
import com.example.ktunsplashapp.presentation.components.CountLabel
import com.example.ktunsplashapp.presentation.ui.theme.Dimensions
import com.example.ktunsplashapp.presentation.ui.theme.KtUnsplashAppTheme

@Composable
fun PhotoThumbnail(
    photo: Photo,
    onClick: (Photo) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .heightIn(min = Dimensions.Space200)
            .clickable { onClick(photo) },
        contentAlignment = Alignment.BottomCenter,
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        AsyncImage(
            model = photo.imageUrl,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(Dimensions.Space8),
        ) {
            PhotoDescriptionColumn(photo)
            Spacer(modifier = Modifier.weight(1f))
            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = photo.likes ?: 0,
                iconTint = Color.Magenta,
                contentDescription = "likes",
                color = Color.White,
            )
        }
    }
}

@Composable
fun PhotoDescriptionColumn(photo: Photo) {
    Column(
        modifier = Modifier.fillMaxWidth(0.8f),
    ) {
        Text(
            text = photo.description ?: "No description",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = photo.photographer ?: "Unknown photographer",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun PhotoThumbnailPreview() {
    val photo = Photo(
        photoId = "",
        description = "Image description",
        likes = 300,
        imageUrl = "https://images.unsplash.com/photo-1665686377065-08ba896d16fd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxfHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=800&q=60",
        photographer = "Surface",
    )
    KtUnsplashAppTheme {
        PhotoThumbnail(photo = photo, onClick = {})
    }
}