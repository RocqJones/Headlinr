package com.zonkesoft.headlinr.android.ui.common.texts

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
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.zonkesoft.headlinr.android.R

@Composable
fun MediumText(
    text: String,
    textColor: Color,
    textDecoration: TextDecoration? = TextDecoration.None,
    textAlign: TextAlign?= TextAlign.Start,
    fontSize: TextUnit? = 16.sp,
    modifier: Modifier = Modifier
) {
    val style = TextStyle(
        color = textColor,
        fontWeight = FontWeight.Medium,
        fontSize = fontSize ?: 16.sp
    )
    val fontFamily: FontFamily? = ResourcesCompat.getFont(
        LocalContext.current.applicationContext, R.font.poppins_medium
    )?.let { font -> FontFamily(font) }

    Text(
        text = text,
        style = style,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        textAlign = textAlign ?: TextAlign.Start
    )
}