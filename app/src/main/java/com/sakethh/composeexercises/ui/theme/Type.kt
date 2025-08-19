package com.sakethh.composeexercises.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sakethh.composeexercises.R

val capriolaFontFamily = FontFamily(
    Font(R.font.capriola, weight = FontWeight.Normal),
)

val Typography = Typography(
    titleLarge = TextStyle(fontFamily = capriolaFontFamily, fontWeight = FontWeight.SemiBold),
    titleMedium = TextStyle(fontFamily = capriolaFontFamily, fontWeight = FontWeight.Medium),
    titleSmall = TextStyle(fontFamily = capriolaFontFamily, fontWeight = FontWeight.Normal),
)