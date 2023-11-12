package com.example.ktunsplashapp.presentation.search_photos

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ktunsplashapp.presentation.search_photos.components.PhotoThumbnail
import com.example.ktunsplashapp.presentation.search_photos.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotosScreen(
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
        LazyColumn(modifier = Modifier.padding(paddingValue)) {
            items(state.photos) { photo ->
                PhotoThumbnail(photo = photo, onClick = {})
            }
        }
    }
}