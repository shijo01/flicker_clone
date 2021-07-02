package com.flicker.sampleapp.presentation.ui.photo_list

sealed class PhotoListEvent {

    object NewSearchEvent : PhotoListEvent()

    object NextPageEvent : PhotoListEvent()

    //Restore after process death
    object RestoreStateEvent : PhotoListEvent()
}