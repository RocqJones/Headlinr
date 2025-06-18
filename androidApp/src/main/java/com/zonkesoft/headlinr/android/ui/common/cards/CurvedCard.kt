package com.zonkesoft.headlinr.android.ui.common.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zonkesoft.headlinr.android.ui.theme.defaultBackground

@Composable
fun CurvedCard(backgroundColor: Color? = null, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor ?: defaultBackground
        )
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(16.dp)
        ) {
            content()
        }
    }
}

@Composable
fun CurvedCardNoPadding(backgroundColor: Color? = null, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor ?: defaultBackground
        )
    ) {
        Box(
            modifier = Modifier.background(Color.Transparent)
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun CurvedCardPreview() {
    CurvedCard {}
}