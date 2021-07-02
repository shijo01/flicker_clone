package com.flicker.sampleapp.presentation.components

sealed class Error {
    object NoResultFound: Error()
    object NoInternetConnection: Error()
}
