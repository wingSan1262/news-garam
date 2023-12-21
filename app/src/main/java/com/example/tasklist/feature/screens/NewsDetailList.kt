package com.example.tasklist.feature.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import base.compose.white
import com.example.tasklist.base.compose.component.ImageUrlLoader
import com.example.tasklist.base.compose.component.ShowFadeInSlideBottom
import com.example.tasklist.base.compose.component.ShowSlideLeft
import com.example.tasklist.feature.NewViewModel
import com.example.tasklist.feature.navigator.NEWS_CONTENT_SCREEN

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.ArticleDetail(
    newsVm : NewViewModel,
    onBackToMainList : ()->Unit
){
    composable(route = NEWS_CONTENT_SCREEN){

        val article = newsVm.currentSelectedNew

        Column {

            Box(contentAlignment = Alignment.TopStart){
                ImageUrlLoader(
                    url = article.urlToImage ?: "",
                    desc = article.title ?: "",
                )
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = white,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onBackToMainList()
                        }

                )
            }


            ShowFadeInSlideBottom(150) {
                Text(
                    text = article.title ?: "",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                )
            }


            if(!article.author.isNullOrBlank())
                ShowFadeInSlideBottom(200) {
                    Text(
                        text = article.author ?: "",
                        textAlign = TextAlign.Justify,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                    )
                }


            if(!article.source?.name.isNullOrBlank())
                ShowFadeInSlideBottom(200) {
                    Text(
                        text = article.source?.name ?: "",
                        textAlign = TextAlign.Justify,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                    )
                }

            ShowFadeInSlideBottom(250){
                Text(
                    text = if(article.description.isNullOrBlank())
                        "" else "\"${article.description}\"",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 32.dp)
                )
            }

            ShowSlideLeft(300){
                Text(
                    text = article.content ?: "",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                )
            }

        }

    }
}