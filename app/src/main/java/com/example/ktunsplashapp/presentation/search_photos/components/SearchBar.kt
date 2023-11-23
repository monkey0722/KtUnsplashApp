package com.example.ktunsplashapp.presentation.search_photos.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.ktunsplashapp.presentation.ui.theme.Dimensions

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onDone: () -> Unit,
    placeholderText: String = "Search...",
) {
    val showClearButton = searchText.isNotEmpty()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(title = {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                onSearchTextChanged(it)
                if (it.isEmpty()) {
                    keyboardController?.hide()
                }
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimensions.Space2)
                    .focusRequester(focusRequester),
            placeholder = { Text(text = placeholderText) },
            trailingIcon = {
                if (showClearButton) {
                    IconButton(onClick = { onSearchTextChanged("") }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "clear",
                        )
                    }
                }
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions =
                KeyboardActions(onDone = {
                    onDone()
                    keyboardController?.hide()
                }),
        )
    })

    LaunchedEffect(key1 = searchText) {
        focusRequester.requestFocus()
    }
}
