package com.flicker.sampleapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.flicker.sampleapp.R

@Composable
fun PhotoNotFoundView() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = decoupledConstraints()
        ) {
            val animationSpec = remember {
                LottieAnimationSpec.RawRes(R.raw.not_found)
            }
            val animationState =
                rememberLottieAnimationState(autoPlay = true, repeatCount = Integer.MAX_VALUE)
            LottieAnimation(

                spec = animationSpec,
                animationState = animationState,
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("lottieAnimationView")
            )
            Text(
                text = "No photos found for the searched keyword",
                modifier = Modifier.layoutId("textMessage").padding(horizontal = 24.dp),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

        }
    }
}

fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val lottieView = createRefFor("lottieAnimationView")
        val text = createRefFor("textMessage")

        constrain(lottieView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(text) {
            start.linkTo(lottieView.start)
            end.linkTo(lottieView.end)
            top.linkTo(lottieView.bottom)
        }
    }

}
