package com.zonkesoft.headlinr.android.ui.common.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText

@Composable
fun LottieLoader(message: String = "", textColor: Color) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.circular_loading)
            )
            val progress by animateLottieCompositionAsState(
                composition, iterations = LottieConstants.IterateForever // Loop indefinitely
            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(200.dp)
            )
            if (message.isNotEmpty()) {
                SpacerCommon(size = 8, isVertical = true)
                RegularText(
                    text = message,
                    textColor = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}