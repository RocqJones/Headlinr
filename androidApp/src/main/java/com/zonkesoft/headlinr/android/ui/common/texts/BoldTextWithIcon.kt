package com.zonkesoft.headlinr.android.ui.common.texts

import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.zonkesoft.headlinr.android.R

@Composable
fun BoldTextWithIcon(
    text: String,
    icon: Painter,
    textColor: Color,
    fontSize: TextUnit,
    textAlign: TextAlign?,
    hasBorder: Boolean = false,
    iconColor: Color = textColor
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .then(
                when {
                    hasBorder -> {
                        Modifier
                            .border(1.dp, iconColor, shape = RoundedCornerShape(6.dp))
                            .padding(6.dp)
                    }
                    else -> {
                        Modifier
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

        BoldText(
            text = text,
            textColor = textColor,
            fontSize = fontSize,
            textAlign = textAlign
        )
    }
}