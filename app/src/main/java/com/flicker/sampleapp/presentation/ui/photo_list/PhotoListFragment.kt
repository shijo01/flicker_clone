package com.flicker.sampleapp.presentation.ui.photo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.flicker.sampleapp.presentation.components.PhotoList
import com.flicker.sampleapp.presentation.components.SearchAppBar
import com.flicker.sampleapp.presentation.ui.photo_list.PhotoListEvent.NewSearchEvent
import com.flicker.sampleapp.ui.theme.ComposeTheme
import com.flicker.sampleapp.presentation.BaseApplication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotoListFragment : Fragment() {
    private val viewModel: PhotoListViewModel by viewModels()

    @Inject
    lateinit var baseApplication: BaseApplication


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeTheme(darkTheme = baseApplication.isDark.value) {
                    Column(
                        modifier = Modifier.background(color = MaterialTheme.colors.background)
                    ) {
                        SearchAppBar(
                            query = viewModel.query.value,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = { viewModel.onEventTriggered(NewSearchEvent) },
                            selectedCategory = viewModel.selectedCategory.value,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition
                        ) {
                            baseApplication.toggleTheme()
                        }

                        PhotoList(
                            isLoading = viewModel.loading.value,
                            photos = viewModel.photos.value,
                            onChangePhotoScrollPosition = {
                                viewModel.onChangePhotoScrollPosition(
                                    it
                                )
                            },
                            page = viewModel.page.value,
                            onEventTriggered = {
                                viewModel.onEventTriggered(it)
                            }
                        )
                    }
                }
            }
        }
    }
}