package com.payor.christmasgreetingcard
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random
private data class Snowflakes (
    val x: Float,
    val initialY: Float,
    val radius: Float,
    val speed: Float,
    val alpha: Float
)
@Composable
fun FallingSnowflakes(modifier: Modifier = Modifier){
    val snowflakes = remember {
        List(150){
            Snowflakes(
                x = Random.nextFloat(),
                initialY = Random.nextFloat(),
                radius = Random.nextFloat() * 3f + 2f,
                speed = Random.nextFloat() * 0.40f + 0.6f,
                alpha = Random.nextFloat() * 0.8f + 0.2f
            )
        }
    }
    val infiniteTransition = rememberInfiniteTransition(label = "Snowflake Transition")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "snowflake_progress"
    )
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasHeight = size.height
        for (snowflake in snowflakes) {
            val startingY = snowflake.initialY * canvasHeight
            val currentY = (startingY + (progress * canvasHeight * snowflake.speed)) % canvasHeight
            drawCircle(
                color = Color.White,
                center = Offset(snowflake.x * size.width, currentY),
                radius = snowflake.radius,
                alpha = snowflake.alpha
            )
        }
    }
}