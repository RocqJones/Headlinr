package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.zonkesoft.headlinr.android.ui.common.cards.CurvedCard
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText

@Composable
fun ErrorMessage(title: String, message: String, textColor: Color) {
    CurvedCard {
        Column(modifier = Modifier.fillMaxWidth()) {
            MediumText(
                text = title,
                textColor = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )

            SpacerCommon(size = 2, isVertical = true)

            RegularText(
                text = message,
                textColor = textColor,
                fontSize = 12.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}