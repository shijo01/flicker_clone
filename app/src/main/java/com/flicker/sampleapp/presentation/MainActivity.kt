package com.flicker.sampleapp.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.flicker.sampleapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


//{
//    "method": "flickr.photos.search",
//    "api_key": "062a6c0c49e4de1d78497d13a7dbb360",
//    "text": "",
//    "format": "json",
//    "nojsoncallback": 1,
//    "per_page": 2,
//    "page": 1
//}