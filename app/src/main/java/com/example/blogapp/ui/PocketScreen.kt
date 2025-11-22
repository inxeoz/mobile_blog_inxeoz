package com.example.blogapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blogapp.data.PostRepository
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PocketScreen(navController: NavController, onMenuClick: () -> Unit) {
    val posts = PostRepository.getPosts()
    var selectedPosts by remember { mutableStateOf(setOf<String>()) }
    val isSelectionMode = selectedPosts.isNotEmpty()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    if (isSelectionMode) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            OutlinedButton(
                                onClick = { /* TODO: Share */ },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = MutedBlue.copy(alpha = 0.2f),
                                    contentColor = MutedBlue
                                ),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("share")
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedButton(
                                onClick = { 
                                    // Remove selected posts from saved
                                    selectedPosts = emptySet()
                                },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = MutedBlue.copy(alpha = 0.2f),
                                    contentColor = MutedBlue
                                ),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("delete")
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            OutlinedButton(
                                onClick = { /* TODO */ },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = MutedBlue,
                                    contentColor = DarkBlueBackground
                                ),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text("Recently added")
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                },
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
                SelectablePocketCard(
                    title = post.title,
                    description = post.content.take(100) + "...",
                    isSelected = selectedPosts.contains(post.id),
                    onClick = { 
                        if (isSelectionMode) {
                            selectedPosts = if (selectedPosts.contains(post.id)) {
                                selectedPosts - post.id
                            } else {
                                selectedPosts + post.id
                            }
                        } else {
                            navController.navigate(com.example.blogapp.Screen.Detail.createRoute(post.id))
                        }
                    },
                    onLongClick = {
                        selectedPosts = if (selectedPosts.contains(post.id)) {
                            selectedPosts - post.id
                        } else {
                            selectedPosts + post.id
                        }
                    }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectablePocketCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MutedBlue.copy(alpha = 0.2f) else androidx.compose.ui.graphics.Color.Transparent
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MutedBlue else MutedBlue.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MutedBlue,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = com.example.blogapp.ui.theme.TextBlueGrey,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
