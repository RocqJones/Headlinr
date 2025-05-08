package com.zonkesoft.headlinr.android.ui.helpers.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.core.content.res.ResourcesCompat
import com.zonkesoft.headlinr.android.R

@Composable
fun RegularText(
    text: String,
    textColor: Color,
    fontSize: TextUnit,
    textAlign: TextAlign?= TextAlign.Start,
    modifier: Modifier = Modifier,
    textDecoration: TextDecoration? = null,
) {
    val style = TextStyle(
        color = textColor,
        fontWeight = FontWeight.Normal,
        fontSize = fontSize,
        textDecoration = textDecoration
    )
    val fontFamily: FontFamily? = ResourcesCompat.getFont(
        LocalContext.current.applicationContext, R.font.poppins_regular
    )?.let { font -> FontFamily(font) }

    Text(
        text = text,
        style = style,
        fontFamily = fontFamily,
        textAlign = textAlign ?: TextAlign.Start
    )
}