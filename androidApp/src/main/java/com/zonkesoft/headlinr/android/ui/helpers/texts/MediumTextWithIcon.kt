package com.zonkesoft.headlinr.android.ui.helpers.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.zonkesoft.headlinr.android.R

@Composable
fun MediumTextWithIcon(
    text: String,
    icon: Painter,
    textColor: Color,
    fontSize: TextUnit,
    alignText: TextAlign?,
    hasBorder: Boolean = false,
    iconColor: Color = textColor,
    bgColor: Color = Color.Transparent,
    paddingSize: Dp = 6.dp,
    clickable: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(
            when {
                hasBorder -> {
                    Modifier.border(1.dp, iconColor, shape = RoundedCornerShape(6.dp))
                        .background(bgColor, shape = RoundedCornerShape(6.dp))
                        .padding(paddingSize)
                        .clickable { clickable() }
                }

                else -> {
                    Modifier
                        .background(bgColor)
                        .clickable { clickable() }
                }
            }
        )
    ) {
        Icon(
            painter = icon,
            contentDescription = stringResource(id = R.string.icon),
            tint = iconColor
        )

        Spacer(modifier = Modifier.width(8.dp))

        MediumText(
            text = text,
            textColor = textColor,
            fontSize = fontSize,
            textAlign = alignText
        )
    }
}