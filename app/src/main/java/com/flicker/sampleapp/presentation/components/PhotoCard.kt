package com.flicker.sampleapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.flicker.sampleapp.R
import com.flicker.sampleapp.domain.model.Photo
import com.flicker.sampleapp.util.loadPicture

@Composable
fun PhotoCard(
    photo: Photo
) {
    val context = LocalContext.current
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 8.dp,
                end = 8.dp
            )
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, photo.id.toString(), Toast.LENGTH_SHORT)
                    .show()
            },
        elevation = 8.dp
    ) {
        Column {
            photo.photoUrl?.let { url ->
                val image = loadPicture(url = url, placeholder = R.drawable.empty).value
                image?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(225.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = photo.title ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.Start),
                    style = MaterialTheme.typography.h3,
                )
            }
        }

    }
}