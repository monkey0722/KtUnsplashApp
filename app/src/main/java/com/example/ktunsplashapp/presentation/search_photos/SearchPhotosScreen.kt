package com.example.ktunsplashapp.presentation.search_photos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ktunsplashapp.presentation.ScreenRoutes
import com.example.ktunsplashapp.presentation.search_photos.components.PhotoThumbnail
import com.example.ktunsplashapp.presentation.search_photos.components.SearchBar
import com.example.ktunsplashapp.presentation.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotosScreen(
    navController: NavController,
    viewModel: SearchPhotosViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            SearchBar(
                searchText = viewModel.query,
                onSearchTextChanged = { viewModel.onQueryChanged(it) },
                onDone = { viewModel.searchPhotos() }
            )
        }
    ) { paddingValue ->
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !state.error.isNullOrBlank() -> {
                    Text(
                        text = state.error,
                        modifier = Modifier.align(Alignment.Center).padding(Dimensions.Space16),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.padding(paddingValue)) {
                        items(state.photos) { photo ->
                            PhotoThumbnail(
                                photo = photo,
                                onClick = {
                                    navController.navigate(ScreenRoutes.PhotoDetailScreen.route + "/${photo.photoId}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}