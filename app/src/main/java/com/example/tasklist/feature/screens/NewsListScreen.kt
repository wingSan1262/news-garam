@file:OptIn(ExperimentalMaterialApi::class)

package com.example.tasklist.feature.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tasklist.base.extensions.asState
import com.example.tasklist.base.extensions.collectResource
import com.example.tasklist.base.extensions.showToast
import com.example.tasklist.feature.NewViewModel
import com.example.tasklist.feature.navigator.NEWS_LIST_SCREEN
import com.example.tasklist.feature.component.NewsList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NavGraphBuilder.NewsListScreen(
    newsViewModel: NewViewModel,
    onOpenDetail : ()->Unit
){
    composable(route = NEWS_LIST_SCREEN){
        val context = LocalContext.current as ComponentActivity
        val (isSuccess, isError,
            isLoading, data,
            _, error) = newsViewModel.newsData.collectResource().asState()

        LaunchedEffect(key1 = true ){
            if(data == null)
                newsViewModel.getNews()
        }

        LaunchedEffect(key1 = isError){
            if(isError)
                context.showToast(error?.message ?: "There's network error")
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Head News", color = Color.White) },
                    elevation = 4.dp
                )
            },
            content = {

                if(data?.articles.isNullOrEmpty())
                    EmptyIllustration()
                else
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = isLoading),
                        onRefresh = {
                            newsViewModel.getNews()
                        },
                    ) {
                        NewsList(tasks = data?.articles ?: listOf()){
                            newsViewModel.currentSelectedNew = it
                            onOpenDetail()
                        }
                    }
            }
        )
    }
}

@Composable
fun EmptyIllustration() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Empty Illustration",
            tint = Color.Gray,
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp),
        )
        Text(
            text = "No Item Found",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}