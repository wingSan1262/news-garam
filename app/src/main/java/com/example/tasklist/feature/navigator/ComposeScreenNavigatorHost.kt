package com.example.tasklist.feature.navigator

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tasklist.feature.NewViewModel
import com.example.tasklist.feature.screens.ArticleDetail
import com.example.tasklist.feature.screens.NewsListScreen

@ExperimentalMaterialApi
@Composable
fun TaskComposeNavigationHost(
    taskViewModel: NewViewModel,
    nav: NavHostController,
){
    val navigator = remember(nav){
        TaskScreenNavigator(nav)
    }

    NavHost(nav, startDestination = NEWS_LIST_SCREEN){
        NewsListScreen(taskViewModel) {
            navigator.navigateToEdit()
        }
        ArticleDetail(taskViewModel){
            navigator.navigateToMain(isPop = true)
        }
    }
}

val NEWS_LIST_SCREEN by lazy { "NEWS_LIST_SCREEN" }
val NEWS_CONTENT_SCREEN by lazy {"NEWS_CONTENT_SCREEN"}