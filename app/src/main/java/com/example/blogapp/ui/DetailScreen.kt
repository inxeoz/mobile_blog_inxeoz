package com.example.blogapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blogapp.data.PostRepository
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue
import com.example.blogapp.ui.theme.TextBlueGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(postId: String, onBackClick: () -> Unit = {}) {
    val post = PostRepository.getPost(postId)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pocket", color = MutedBlue) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MutedBlue)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DarkBlueBackground
                )
            )
        },
        containerColor = DarkBlueBackground,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBlueBackground)
                    .padding(16.dp)
            ) {
                MessageInput()
            }
        }
    ) { innerPadding ->
        if (post != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = post.author,
                        style = MaterialTheme.typography.labelSmall,
                        color = MutedBlue
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { /* TODO */ },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MutedBlue),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue)
                        ) {
                            Text("Follow")
                        }
                        OutlinedButton(
                            onClick = { /* TODO */ },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MutedBlue),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue)
                        ) {
                            Text("read")
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MutedBlue
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "By ${post.author} • ${post.date}",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextBlueGrey
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ActionIcon(Icons.Default.Star)
                    ActionIcon(Icons.Default.Info)
                    ActionIcon(Icons.Default.ArrowDropDown)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = post.content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextBlueGrey
                )
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Post not found",
                    color = TextBlueGrey
                )
            }
        }
    }
}
