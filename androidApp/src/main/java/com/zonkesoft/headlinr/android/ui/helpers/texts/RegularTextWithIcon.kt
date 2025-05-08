package com.zonkesoft.headlinr.android.ui.helpers.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.zonkesoft.headlinr.android.R

@Composable
fun RegularTextWithIcon(
    text: String,
    icon: Painter,
    textColor: Color,
    fontSize: TextUnit,
    alignText: TextAlign?,
    hasBorder: Boolean = false,
    iconColor: Color = textColor,
    bgColor: Color = Color.Transparent,
    textDecoration: TextDecoration? = null,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    clickable: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .then(
                when {
                    hasBorder -> {
                        Modifier
                            .border(1.dp, iconColor, shape = RoundedCornerShape(6.dp))
                            .background(bgColor, shape = RoundedCornerShape(6.dp))
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
        Row(
            verticalAlignment = verticalAlignment,
            modifier = Modifier
                .then(
                    when {
                        hasBorder -> {
                            Modifier
                                .padding(top = 4.dp, bottom = 4.dp, start = 4.dp, end = 6.dp)
                        }
                        else -> {
                            Modifier
                                .background(bgColor)
                        }
                    }
                )
        ) {
            Row(
                verticalAlignment = verticalAlignment,
                modifier = Modifier
                    .then(
                        when {
                            hasBorder -> {
                                Modifier
                                    .padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp)
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

                RegularText(
                    text = text,
                    textColor = textColor,
                    fontSize = fontSize,
                    textAlign = alignText,
                    textDecoration = textDecoration
                )
            }
        }
    }
}