package com.flicker.sampleapp.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun CircularIndeterminateProgressbar(
    isLoading: Boolean = false,
    textVisible: Boolean = true
) {
    if (isLoading) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isPortrait = minWidth < 600.dp
            val constraints = myDecoupledConstrains(portrait = isPortrait)

            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
                constraintSet = constraints
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.layoutId("progressBar")
                )
                if (textVisible) {
                    Text(
                        text = "Loading...",
                        modifier = Modifier
                            .layoutId("progressBarText")
                            .padding(
                                top = if (isPortrait) 4.dp else 0.dp,
                                start = if (isPortrait) 0.dp else 4.dp
                            ),
                        style = TextStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),

                        )
                }
            }
        }
    }
}


private fun myDecoupledConstrains(portrait: Boolean): ConstraintSet {
    if (portrait) {
        return ConstraintSet {
            val horizontalGuideline = createGuidelineFromTop(fraction = .15f)
            val progressBar = createRefFor("progressBar")
            val progressBarText = createRefFor("progressBarText")

            constrain(progressBar) {
                top.linkTo(horizontalGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(progressBarText) {
                top.linkTo(progressBar.bottom)
                start.linkTo(progressBar.start)
                end.linkTo(progressBar.end)
            }
        }
    } else {
        return ConstraintSet {
            val horizontalGuideline = createGuidelineFromTop(fraction = .15f)
            val progressBar = createRefFor("progressBar")
            val progressBarText = createRefFor("progressBarText")

            constrain(progressBar) {
                top.linkTo(horizontalGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(progressBarText) {
                top.linkTo(progressBar.top)
                start.linkTo(progressBar.end)
                bottom.linkTo(progressBar.bottom)
            }
        }
    }
}