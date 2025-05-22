package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularTextWithIcon
import com.zonkesoft.headlinr.android.ui.theme.logoColor
import com.zonkesoft.headlinr.data.vm.InterfaceViewModel

@Composable
fun MenuContent(
    textColor: Color,
    interfaceViewModel: InterfaceViewModel
) {
    val topics = interfaceViewModel.topics.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Limit to 5 search items
        /*BoldText(
            text = "Recent searches...",
            textColor = textColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )

        SpacerCommon(8, isVertical = true)

        LazyColumn {}

        SpacerCommon(16, isVertical = true)*/

        BoldText(
            text = stringResource(R.string.topics),
            textColor = textColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )

        SpacerCommon(8, isVertical = true)

        LazyColumn {
            items(topics.value.size) { index ->
                val topicItem = topics.value[index]
                RegularTextWithIcon(
                    text = topicItem.title,
                    icon = painterResource(id = R.drawable.outline_arrow_outward_20),
                    textColor = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    iconColor = logoColor,
                    hasBorder = false
                )

                SpacerCommon(8, isVertical = true)
                HorizontalDivider()
                SpacerCommon(8, isVertical = true)
            }
        }
    }
}