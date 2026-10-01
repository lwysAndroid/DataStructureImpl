package com.example.firstapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

/**
 * ⚠️ WARNING: INFINITE RELAYOUT LOOP / PING-PONG FEEDBACK
 *
 * What happens when this composable runs:
 * 1. UNIT MISMATCH (px vs dp): `onSizeChanged` returns height in Pixels (px),
 *    but `.height()` expects Density-Independent Pixels (dp).
 * 2. EXPONENTIAL GROWTH: On high-density screens (e.g., 3x density), 10dp is measured
 *    as 30px. This updates the sibling height to 30dp, which next frame measures
 *    as 90px, then 270dp... causing both columns to exponentially explode in size.
 * 3. LAYOUT PHASE LOOP: Modifying State inside `onSizeChanged` mutates state during
 *    the Layout phase, constantly invalidating the Composition phase and forcing a
 *    continuous frame-by-frame recomposition loop.
 */
@Composable
fun InfiniteRelayoutLoopView() {
    var firstColumHeight by remember() { mutableStateOf(0.dp) }
    var secondColumHeight by remember() { mutableStateOf(10.dp) }
    //Set a debug point right at the line of this container Column
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .height(height = firstColumHeight)
                .onSizeChanged { newSizeFirstColumn ->
                    secondColumHeight = newSizeFirstColumn.height.dp
                }
                .background(color = Color.Red)
        ) {

        }
        Column(
            Modifier
                .height(height = secondColumHeight)
                .onSizeChanged { newSizeSecondColumn ->
                    firstColumHeight = newSizeSecondColumn.height.dp
                }
                .background(color = Color.Green)
        ) {
        }
    }
}