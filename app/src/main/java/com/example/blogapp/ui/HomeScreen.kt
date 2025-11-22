package com.example.blogapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blogapp.data.PostRepository
import com.example.blogapp.model.Post
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onMenuClick: () -> Unit = {}) {
    val posts = PostRepository.getPosts()
    var savedPosts by remember { mutableStateOf(setOf<String>()) }

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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PocketSearchBar(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MutedBlue),
                    border = BorderStroke(1.dp, MutedBlue)
                ) {
                    Text("Most Popular")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(posts) { post ->
                    PocketCard(
                        title = post.title,
                        description = post.content.take(100) + "...",
                        onClick = { navController.navigate(com.example.blogapp.Screen.Detail.createRoute(post.id)) },
                        isSaved = savedPosts.contains(post.id),
                        onSaveClick = {
                            savedPosts = if (savedPosts.contains(post.id)) {
                                savedPosts - post.id
                            } else {
                                savedPosts + post.id
                            }
                        }
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        PocketButton(text = "more", onClick = { /* TODO */ })
                    }
                }
            }
        }
    }
}
