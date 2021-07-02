package com.flicker.sampleapp.presentation.ui.photo_list

import com.flicker.sampleapp.presentation.components.Error

sealed class PhotoListState {

    object Loading : PhotoListState()
    class Success(val isMoreLoading: Boolean = false) : PhotoListState()
    class Error(val error: com.flicker.sampleapp.presentation.components.Error) : PhotoListState()
}
