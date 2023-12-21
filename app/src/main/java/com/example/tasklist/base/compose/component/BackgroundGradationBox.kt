package com.example.tasklist.base.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import base.compose.colorAccent
import base.compose.colorPrimary
import base.compose.colorPrimaryDark

@Composable
fun BackgroundGradationBox(
    modifier: Modifier,
    isRight: Boolean,
    content: @Composable ()->Unit
){


    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        if(isRight) Color.Transparent else colorPrimary.copy(alpha = 0.8f),
                        if(isRight) colorPrimary.copy(alpha = 0.8f) else Color.Transparent
                    )
                )
            ),
        contentAlignment = if(!isRight) Alignment.CenterStart
        else Alignment.CenterEnd
    ) {
        content()
    }
}