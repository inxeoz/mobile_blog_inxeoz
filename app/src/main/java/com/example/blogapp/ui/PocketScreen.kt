package com.example.blogapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blogapp.data.PostRepository
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketScreen(navController: NavController, onMenuClick: () -> Unit) {
    val posts = PostRepository.getPosts()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pocket", color = MutedBlue) },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = MutedBlue)
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = MutedBlue)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DarkBlueBackground
                )
            )
        },
        containerColor = DarkBlueBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            
            items(posts.take(3)) { post ->
                PocketCard(
                    title = post.title,
                    description = post.content.take(100) + "...",
                    onClick = { navController.navigate(com.example.blogapp.Screen.Detail.createRoute(post.id)) }
                )
            }
            
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    PocketButton(text = "more", onClick = { /* TODO */ })
                }
            }
        }
    }
}
