package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel

@Composable
fun ProfileContent(
    textColor: Color,
    interfaceViewModel: InterfaceViewModel
) {
    val menuList = interfaceViewModel.menuItems.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        BoldText(
            text = stringResource(R.string.quick_actions),
            textColor = textColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )

        SpacerCommon(8, isVertical = true)

        LazyColumn {
            items(menuList.value.size) { index ->
                val menuItem = menuList.value[index]
                RegularText(
                    text = menuItem.title,
                    textColor = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
                SpacerCommon(8, isVertical = true)
                HorizontalDivider()
                SpacerCommon(8, isVertical = true)
            }
        }
    }
}