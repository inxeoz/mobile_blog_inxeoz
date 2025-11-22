package com.example.blogapp.data

import com.example.blogapp.model.Post

object PostRepository {
    fun getPosts(): List<Post> {
        return listOf(
            Post(
                id = "1",
                title = "Getting Started with Jetpack Compose",
                summary = "Learn the basics of building UI with Compose.",
                content = "Jetpack Compose is Android's modern toolkit for building native UI. It simplifies and accelerates UI development on Android. \n\nIn this post, we will explore the fundamental concepts of Compose, including Composable functions, Modifiers, and State management.",
                author = "Android Dev",
                date = "2023-10-27"
            ),
            Post(
                id = "2",
                title = "Kotlin Coroutines Explained",
                summary = "Understand asynchronous programming with Coroutines.",
                content = "Coroutines are a powerful feature in Kotlin that allow you to write asynchronous code in a sequential manner. They are lightweight threads that can be suspended and resumed without blocking the main thread. \n\nWe'll cover launch, async, dispatchers, and structured concurrency.",
                author = "Kotlin Fan",
                date = "2023-10-28"
            ),
            Post(
                id = "3",
                title = "Material Design 3 in Android",
                summary = "Applying the latest design system to your app.",
                content = "Material Design 3 is the latest evolution of Google's open-source design system. It includes new features like dynamic color, updated components, and better accessibility support. \n\nLearn how to implement MD3 in your Android app using Jetpack Compose Material3 library.",
                author = "Design Guru",
                date = "2023-10-29"
            ),
             Post(
                id = "4",
                title = "Architecture Components",
                summary = "Building robust apps with MVVM.",
                content = "Android Architecture Components are a collection of libraries that help you design robust, testable, and maintainable apps. \n\nKey components include ViewModel, LiveData (or Flow), and Room. We will see how they fit together in the MVVM pattern.",
                author = "Arch Expert",
                date = "2023-10-30"
            )
        )
    }

    fun getPost(id: String): Post? {
        return getPosts().find { it.id == id }
    }
}
