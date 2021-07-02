package com.flicker.sampleapp.presentation.ui.photo_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flicker.sampleapp.domain.model.Photo
import com.flicker.sampleapp.network.GenericNetworkResponse
import com.flicker.sampleapp.presentation.ui.photo_list.PhotoListEvent.*
import com.flicker.sampleapp.repository.FlickerPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

const val STATE_KEY_PAGE = "photo.state.page.key"
const val STATE_KEY_QUERY = "photo.state.query.key"
const val STATE_KEY_LIST_POSITION = "photo.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "photo.state.query.selected_category"

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val photoRepository: FlickerPhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val photos: MutableState<List<Photo>> = mutableStateOf(listOf())
    val query: MutableState<String> = mutableStateOf("nature")
    val selectedCategory: MutableState<PhotoCategory?> = mutableStateOf(null)
    private var selectedCategoryPosition: Int = 0
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
    private var photoListScrollPosition = 0


    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let {
            setPage(it)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let {
            setQuery(it)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let {
            setListScrollPosition(it)
        }
        savedStateHandle.get<PhotoCategory>(STATE_KEY_SELECTED_CATEGORY)?.let {
            setSelectedCategory(it)
        }

        if (photoListScrollPosition != 0) {
            onEventTriggered(RestoreStateEvent)
        } else {
            onEventTriggered(NewSearchEvent)
        }
    }

    fun onEventTriggered(event: PhotoListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is NewSearchEvent -> {
                        newSearch()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            } catch (e: Exception) {
                Log.e("PHOTO_LIST", e.toString())
            }
        }
    }

    private suspend fun restoreState() {
        loading.value = true
        val results = mutableListOf<Photo>()
        for (p in 1..page.value) {
            val result = photoRepository.search(
                query.value,
                p
            )
            when (result) {
                is GenericNetworkResponse.Success -> {
                    results.addAll(result.value)
                }
                is GenericNetworkResponse.GenericError -> {
                }
            }
        }
        photos.value = results
        loading.value = false
    }

    private suspend fun newSearch() {
        loading.value = true
        resetSearchState()
        delay(2000)
        Log.e("FPHOTO", "init")
        val result = photoRepository.search(
            query.value,
            1
        )
        when (result) {
            is GenericNetworkResponse.Success -> {
                photos.value = result.value
            }
            is GenericNetworkResponse.GenericError -> {

            }
        }
        loading.value = false
        Log.e("FPHOTO", result.toString())
    }

    private suspend fun nextPage() {
        if (photoListScrollPosition + 1 >= page.value * PAGE_SIZE) {
            loading.value = true
            incrementPage()
            //just to see the progressbar
            delay(1000)
            if (page.value > 1) {
                val result = photoRepository.search(
                    query.value,
                    page.value
                )
                when (result) {
                    is GenericNetworkResponse.Success -> {
                        appendPhotos(result.value)
                    }
                    is GenericNetworkResponse.GenericError -> {

                    }
                }
            }
            loading.value = false
        }
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    fun onChangePhotoScrollPosition(position: Int) {
        setListScrollPosition(position = position)
    }

    private fun appendPhotos(photos: List<Photo>) {
        val currentList = ArrayList(this.photos.value)
        currentList.addAll(photos)
        this.photos.value = currentList
    }

    fun onQueryChanged(query: String) {
        setQuery(query = query)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int) {
        selectedCategoryPosition = position
    }

    private fun clearSelectedCategory() {
        setSelectedCategory(null)
    }

    private fun resetSearchState() {
        photos.value = listOf()
        page.value = 1
        onChangePhotoScrollPosition(0)
        if (selectedCategory.value?.value != query.value) {
            clearSelectedCategory()
        }
    }

    private fun setListScrollPosition(position: Int) {
        photoListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setSelectedCategory(category: PhotoCategory?) {
        selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }

}