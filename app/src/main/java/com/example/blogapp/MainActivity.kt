package com.example.blogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.blogapp.ui.DetailScreen
import com.example.blogapp.ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BlogApp()
                }
            }
        }
    }
}

@Composable
fun BlogApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val screen = currentScreen) {
        is Screen.Home -> {
            HomeScreen(onPostClick = { postId ->
                currentScreen = Screen.Detail(postId)
            })
        }
        is Screen.Detail -> {
            DetailScreen(postId = screen.postId, onBackClick = {
                currentScreen = Screen.Home
            })
        }
    }
}

sealed class Screen {
    object Home : Screen()
    data class Detail(val postId: String) : Screen()
}
