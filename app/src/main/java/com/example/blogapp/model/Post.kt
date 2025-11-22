package com.example.blogapp.model

data class Post(
    val id: String,
    val title: String,
    val summary: String,
    val content: String,
    val author: String,
    val date: String
)
