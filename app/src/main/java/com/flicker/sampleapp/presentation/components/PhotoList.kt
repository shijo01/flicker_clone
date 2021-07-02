package com.flicker.sampleapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flicker.sampleapp.domain.model.Photo
import com.flicker.sampleapp.presentation.ui.photo_list.PAGE_SIZE
import com.flicker.sampleapp.presentation.ui.photo_list.PhotoListEvent
import com.flicker.sampleapp.presentation.ui.photo_list.PhotoListState

@Composable
fun PhotoList(
    apiState: PhotoListState,
    photos: List<Photo>,
    onChangePhotoScrollPosition: (Int) -> Unit,
    page: Int,
    onEventTriggered: (PhotoListEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (apiState) {
            is PhotoListState.Error -> {
                PhotoNotFoundView(apiState.error)
            }
            is PhotoListState.Loading -> {
                CircularIndeterminateProgressbar(true)
            }
            is PhotoListState.Success -> {
                if (photos.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(
                            items = photos
                        ) { index, photo ->
                            onChangePhotoScrollPosition(index)
                            if (index + 1 >= (page * PAGE_SIZE) && apiState !is PhotoListState.Loading) {
                                onEventTriggered(PhotoListEvent.NextPageEvent)
                            }
                            PhotoCard(photo = photo)
                        }
                    }
                    CircularIndeterminateProgressbar(apiState.isMoreLoading)
                } else {
                    PhotoNotFoundView()
                }
            }
        }
    }
}