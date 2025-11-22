package com.example.blogapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(onMenuClick: () -> Unit) {
    var expandedSection by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Explore", color = MutedBlue) },
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
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                PocketSearchBar()
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MutedBlue),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue)
                    ) {
                        Text("Most Popular")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.height(300.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(6) { index ->
                        val categories = listOf(
                            "Politics" to Color(0xFFB0BEC5),
                            "Diary" to Color(0xFFFFCC80),
                            "Story" to Color(0xFF90A4AE),
                            "Meditation" to Color(0xFFC5E1A5),
                            "Books" to Color(0xFFCE93D8),
                            "Technology" to Color(0xFF80DEEA)
                        )
                        CategoryCard(
                            title = categories[index].first,
                            color = categories[index].second
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
                ExpandableSection(
                    title = "Life Lesson",
                    isExpanded = expandedSection == "life",
                    onToggle = { expandedSection = if (expandedSection == "life") null else "life" }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                ExpandableSection(
                    title = "Mobile Application Development",
                    isExpanded = expandedSection == "mobile",
                    onToggle = { expandedSection = if (expandedSection == "mobile") null else "mobile" },
                    items = listOf("Android Dev.", "React Native", "Tips And Tricks", "Native Ios & Android Apis", "Ios MVP Ref", "Rust In Android")
                )
                Spacer(modifier = Modifier.height(16.dp))
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

@Composable
fun ExpandableSection(
    title: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    items: List<String> = emptyList()
) {
    Column {
        OutlinedButton(
            onClick = onToggle,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MutedBlue.copy(alpha = 0.2f),
                contentColor = MutedBlue
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, MutedBlue)
        ) {
            Text(title, modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.then(
                    if (isExpanded) Modifier else Modifier
                )
            )
        }
        
        if (isExpanded && items.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items.size) { index ->
                    val colors = listOf(
                        Color(0xFFB0BEC5),
                        Color(0xFF80DEEA),
                        Color(0xFFFFCC80),
                        Color(0xFFCE93D8),
                        Color(0xFF80DEEA),
                        Color(0xFF90A4AE)
                    )
                    CategoryCard(
                        title = items[index],
                        color = colors[index % colors.size]
                    )
                }
            }
        }
    }
}
