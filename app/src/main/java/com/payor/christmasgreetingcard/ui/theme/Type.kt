package com.payor.christmasgreetingcard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = mountainsOfChristmas,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = mountainsOfChristmas,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    )
)
