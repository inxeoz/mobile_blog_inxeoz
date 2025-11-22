package com.example.blogapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue
import com.example.blogapp.ui.theme.TextBlueGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    var selectedTheme by remember { mutableStateOf("dark") }
    var selectedLanguage by remember { mutableStateOf("english") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", color = MutedBlue) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            // Profile Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MutedBlue.copy(alpha = 0.3f))
                        .border(2.dp, MutedBlue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = MutedBlue,
                        modifier = Modifier.size(40.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = "i am sotware developer and book reader , i love to contribute",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.Transparent,
                            focusedBorderColor = MutedBlue,
                            unfocusedBorderColor = MutedBlue.copy(alpha = 0.5f)
                        ),
                        textStyle = MaterialTheme.typography.bodySmall.copy(color = TextBlueGrey),
                        maxLines = 3
                    )
                }
                
                IconButton(onClick = { /* TODO */ }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MutedBlue)
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "ravi kumar",
                style = MaterialTheme.typography.labelSmall,
                color = TextBlueGrey,
                modifier = Modifier.padding(start = 96.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Username
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "username : ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlueGrey,
                    modifier = Modifier.padding(start = 96.dp)
                )
                Text(
                    text = "@ravi234hk",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedBlue
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MutedBlue, modifier = Modifier.size(16.dp))
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Email
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "email : ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlueGrey,
                    modifier = Modifier.padding(start = 96.dp)
                )
                Text(
                    text = "ravi123@hotmail.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedBlue
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MutedBlue, modifier = Modifier.size(16.dp))
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // App Preference
            Text(
                text = "App preference",
                style = MaterialTheme.typography.titleMedium,
                color = TextBlueGrey
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Theme Selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "theme :",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlueGrey,
                    modifier = Modifier.width(100.dp)
                )
                
                Row(
                    modifier = Modifier
                        .border(1.dp, MutedBlue, RoundedCornerShape(20.dp))
                        .padding(4.dp)
                ) {
                    OutlinedButton(
                        onClick = { selectedTheme = "dark" },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (selectedTheme == "dark") MutedBlue else Color.Transparent,
                            contentColor = if (selectedTheme == "dark") DarkBlueBackground else MutedBlue
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("dark")
                    }
                    OutlinedButton(
                        onClick = { selectedTheme = "light" },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (selectedTheme == "light") MutedBlue else Color.Transparent,
                            contentColor = if (selectedTheme == "light") DarkBlueBackground else MutedBlue
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("light")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Language Selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "language :",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlueGrey,
                    modifier = Modifier.width(100.dp)
                )
                
                OutlinedButton(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MutedBlue,
                        contentColor = DarkBlueBackground
                    ),
                    border = BorderStroke(1.dp, MutedBlue),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("english")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // More Settings
            Text(
                text = "more settings",
                style = MaterialTheme.typography.titleMedium,
                color = TextBlueGrey
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MutedBlue.copy(alpha = 0.2f),
                        contentColor = MutedBlue
                    ),
                    border = BorderStroke(1.dp, MutedBlue),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("blogs settings")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.size(16.dp))
                }
                
                OutlinedButton(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MutedBlue.copy(alpha = 0.2f),
                        contentColor = MutedBlue
                    ),
                    border = BorderStroke(1.dp, MutedBlue),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("editor settings")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
