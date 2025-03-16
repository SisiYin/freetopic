package com.example.freetopic.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen(modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Info",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.CenterHorizontally) // 仅水平居中
        )

        Text(
            text = "This is a movie app that fetches and displays current movies.",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 24.dp)
        )


        // 使用的API
        Text(
            text = "App uses The Movie Database (TMDb) API available on https://www.themoviedb.org/3/",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 开发者信息
        Text(
            text = "Developed by Yiling Chen",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 24.dp)
        )

    }
}