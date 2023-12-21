package com.example.tasklist.feature.navigator

import androidx.navigation.NavHostController

class TaskScreenNavigator(
    val nav : NavHostController
){
    fun navigateToMain (isPop : Boolean = false) {
        nav.navigate(
            route = NEWS_LIST_SCREEN
        ){ if(isPop) popUpTo(NEWS_LIST_SCREEN) }
    }

    fun navigateToEdit() {
        nav.navigate(
            route = NEWS_CONTENT_SCREEN
        )
    }
}