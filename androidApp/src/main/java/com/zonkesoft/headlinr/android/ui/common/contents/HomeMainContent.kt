package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.BoldTextWithIcon
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.android.ui.theme.fireColor
import com.zonkesoft.headlinr.android.ui.theme.linkColor
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainContent(
    navController: NavHostController,
    textColor: Color,
    interfaceViewModel: InterfaceViewModel
) {
    val todayDate by interfaceViewModel.todayDate.collectAsState()
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        HorizontalDivider()
        SpacerCommon(size = 8, isVertical = true)

        RegularText(
            text = todayDate.orEmpty(),
            textColor = textColor,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        SpacerCommon(size = 8, isVertical = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldText(
                text = stringResource(R.string.top_stories),
                textColor = textColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )

            MediumText(
                text = stringResource(R.string.see_all),
                textColor = linkColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }

        SpacerCommon(size = 16, isVertical = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldTextWithIcon(
                text = stringResource(R.string.trending),
                textColor = textColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                icon = painterResource(R.drawable.outline_local_fire_department_24),
                iconColor = fireColor,
            )

            MediumText(
                text = stringResource(R.string.see_all),
                textColor = linkColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }

        SpacerCommon(size = 16, isVertical = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldText(
                text = stringResource(R.string.highlights),
                textColor = textColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )

            MediumText(
                text = stringResource(R.string.see_all),
                textColor = linkColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}