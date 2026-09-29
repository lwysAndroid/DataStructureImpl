package com.example.firstapp

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PulseRateTestScreen() {
    // Allow the pulse rate to be configured, so it can be sped up if the user is running
    // out of time
    var pulseRateMs by remember { mutableLongStateOf(3000L) }
    val alphaBoxColor = remember { Animatable(1f) }
    val animatedEffect: suspend CoroutineScope.() -> Unit =
        { // Restart the effect when the pulse rate changes
            alphaBoxColor.snapTo(1f)
            while (isActive) {
                delay(pulseRateMs.milliseconds) // Pulse the alphaBoxColor every pulseRateMs to alert the user
                alphaBoxColor.animateTo(0f, animationSpec = tween(3500))
                alphaBoxColor.animateTo(1f, animationSpec = tween(3500))
            }
        }

    LaunchedEffect(pulseRateMs) { // Restart the effect when the pulse rate changes
        animatedEffect()
    }

    // UI Layout to test the animation and state changes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Pulsing visual indicator using the animated alphaBoxColor
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .alpha(alphaBoxColor.value)
                        .background(Color.Red, shape = CircleShape)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Current Pulse Rate: ${pulseRateMs}ms",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Current Color: ${alphaBoxColor.value}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Interactive controls to alter pulseRateMs
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (pulseRateMs > 500L) pulseRateMs -= 500L
                }
            ) {
                Text("Speed Up (-500ms)")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    pulseRateMs += 500L
                }
            ) {
                Text("Slow Down (+500ms)")
            }
        }
    }
}