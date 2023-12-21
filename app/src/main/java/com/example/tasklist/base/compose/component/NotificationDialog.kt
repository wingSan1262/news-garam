@file:OptIn(ExperimentalMaterialApi::class)

package com.example.tasklist.base.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.compose.white

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommonNotificationDialog (
    modalBottomSheetState: ModalBottomSheetState,
    title: String = "",
    message: String = "",
    positiveButton: String = "",
    negativeButton: String = "",
    onPositive: ()->Unit = {},
    onNegative: ()->Unit = {},
){
    TeaModalBottomSheet(
        modalBottomSheetState,
        onNegative = onNegative,
        negativeButton = negativeButton,
        positiveButton = positiveButton,
        onPositive = onPositive,
        backgroundColor = white
    ){
        Column(Modifier.padding(horizontal = 16.dp)){
            Text(title, fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
            Text(
                message,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
        }
    }
}