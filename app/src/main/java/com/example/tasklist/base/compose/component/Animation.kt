package com.example.tasklist.base.compose.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ShowFadeInSlideBottom(
    delay: Long,
    content: @Composable ()->Unit,
){

    var isShow by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        delay(delay)
        isShow = true
    }

    val animation =

    AnimatedVisibility(
        visible = isShow,
        enter = fadeIn(tween(500)) + slideInVertically(
            tween(500),
            initialOffsetY = {-it}
        ),
        exit = fadeOut(tween(800)) +
                slideOutVertically(
                    tween(800)
                )
    ) {
        content()
    }
}

@Composable
fun ShowSlideLeft(
    delay: Long,
    isFromRight: Boolean = false,
    content: @Composable ()->Unit,
){
    var isShow by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        delay(delay)
        isShow = true
    }

    val animation =
        AnimatedVisibility(
            visible = isShow,
            enter = fadeIn(tween(800)) + slideInHorizontally(
                tween(800),
                initialOffsetX = { if (isFromRight) it else -it}
            )
        ) {
            content()
        }
}

