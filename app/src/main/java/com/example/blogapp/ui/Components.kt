package com.example.blogapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blogapp.ui.theme.DarkBlueBackground
import com.example.blogapp.ui.theme.MutedBlue
import com.example.blogapp.ui.theme.TextBlueGrey

@Composable
fun PocketCard(
    title: String,
    description: String,
    onClick: () -> Unit,
    isSaved: Boolean = false,
    onSaveClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MutedBlue, RoundedCornerShape(0.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
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
                color = TextBlueGrey,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                OutlinedButton(
                    onClick = onSaveClick,
                    border = BorderStroke(1.dp, if (isSaved) Color(0xFF4CAF50) else MutedBlue),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSaved) Color(0xFF4CAF50).copy(alpha = 0.1f) else Color.Transparent,
                        contentColor = if (isSaved) Color(0xFF4CAF50) else TextBlueGrey
                    )
                ) {
                    Text(if (isSaved) "saved" else "save")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        if (isSaved) Icons.Default.Check else Icons.Default.Add,
                        contentDescription = null,
                        tint = if (isSaved) Color(0xFF4CAF50) else MutedBlue
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketSearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("questions", color = DarkBlueBackground) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = DarkBlueBackground) },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MutedBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PocketButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, MutedBlue),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = DarkBlueBackground,
            contentColor = MutedBlue
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun PocketDrawer(
    onNavigate: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(DarkBlueBackground)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "blogz",
                style = MaterialTheme.typography.titleLarge,
                color = MutedBlue
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = MutedBlue)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        
        val items = listOf(
            "home" to Icons.Default.Home,
            "explore" to Icons.Default.Search,
            "pocket" to Icons.Default.Star
        )
        
        items.forEach { (label, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(label) }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, contentDescription = null, tint = MutedBlue)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MutedBlue
                )
            }
            Divider(color = MutedBlue.copy(alpha = 0.2f))
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = MutedBlue)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "ravi kumar",
                style = MaterialTheme.typography.bodyLarge,
                color = MutedBlue
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onNavigate("settings") }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MutedBlue)
            }
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MutedBlue
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MutedBlue.copy(alpha = 0.2f), RoundedCornerShape(28.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "send message",
            style = MaterialTheme.typography.bodyMedium,
            color = TextBlueGrey,
            modifier = Modifier.weight(1f)
        )
        Icon(Icons.Default.Face, contentDescription = null, tint = Color.Yellow)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(Icons.Default.Send, contentDescription = null, tint = MutedBlue)
    }
}

@Composable
fun ActionIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(1.dp, MutedBlue, RoundedCornerShape(50))
            .padding(8.dp)
    ) {
        Icon(icon, contentDescription = null, tint = MutedBlue)
    }
}
