package com.example.tasklist.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasklist.base.ui.theme.TasklistTheme
import com.example.tasklist.feature.navigator.TaskComposeNavigationHost

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    val taskViewModel: NewViewModel by viewModels()

    private lateinit var navController : NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasklistTheme {
                navController = rememberNavController()
                TaskComposeNavigationHost(taskViewModel, navController)
            }
        }
    }
}
