package com.zonkesoft.headlinr.android.ui.common.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerCommon(size : Int, isVertical : Boolean = false, isHorizontal : Boolean = false) {
    when {
        isVertical -> {
            Spacer(modifier = Modifier.height(size.dp))
        }
        isHorizontal -> {
            Spacer(modifier = Modifier.width(size.dp))
        }
        else -> {
            Spacer(modifier = Modifier.width(size.dp).height(size.dp))
        }
    }
}