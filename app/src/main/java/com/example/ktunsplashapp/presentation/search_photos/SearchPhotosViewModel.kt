package com.example.ktunsplashapp.presentation.search_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktunsplashapp.domain.use_cases.SearchPhotosUseCase
import com.example.ktunsplashapp.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchPhotosViewModel
    @Inject
    constructor(
        private val searchPhotosUseCase: SearchPhotosUseCase,
    ) : ViewModel() {
        private val _state = mutableStateOf(SearchPhotosState())
        val state: State<SearchPhotosState> = _state

        private var _query by mutableStateOf("Tokyo")
        val query: String get() = _query

        init {
            searchPhotos()
        }

        fun onQueryChanged(queryString: String) {
            _query = queryString
        }

        fun searchPhotos() {
            _state.value = SearchPhotosState(isLoading = true)
            searchPhotosUseCase(_query).onEach { result ->
                _state.value =
                    when (result) {
                        is NetworkResponse.Success -> {
                            SearchPhotosState(
                                isLoading = false,
                                photos = result.data ?: emptyList(),
                            )
                        }
                        is NetworkResponse.Failure -> {
                            SearchPhotosState(
                                isLoading = false,
                                error = result.error ?: "An unexpected error occurred",
                            )
                        }
                        is NetworkResponse.Loading -> {
                            SearchPhotosState(isLoading = true)
                        }
                    }
            }.launchIn(viewModelScope)
        }
    }
