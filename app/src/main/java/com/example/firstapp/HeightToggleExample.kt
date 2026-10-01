package com.example.firstapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

@Composable
fun HeightToggleExample(modifier: Modifier= Modifier) {
    var firstColumnHeight by remember { mutableStateOf(0.dp) }
    var secondColumnHeight by remember { mutableStateOf(10.dp) }

    Column(modifier = modifier
        .fillMaxWidth()
        .background(Color.DarkGray)) {
        // Button to toggle/swap heights cleanly
        Button(onClick = {
            if (firstColumnHeight == 0.dp) {
                firstColumnHeight = 10.dp
                secondColumnHeight = 0.dp
            } else {
                firstColumnHeight = 0.dp
                secondColumnHeight = 10.dp
            }
        }) {
            Text("Toggle Column Heights")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(firstColumnHeight)
                .background(Color.Red)
                .onSizeChanged { _ ->
                    secondColumnHeight = if (secondColumnHeight > 0.dp) {
                        0.dp
                    } else {
                        10.dp
                    }
                }
        ) {}

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(secondColumnHeight)
                .background(Color.Green)
                .onSizeChanged { _ ->
                    firstColumnHeight = if (firstColumnHeight > 0.dp) {
                        0.dp
                    } else {
                        10.dp
                    }
                }
        ) {}
    }
}