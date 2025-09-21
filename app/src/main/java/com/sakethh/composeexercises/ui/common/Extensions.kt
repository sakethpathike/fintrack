package com.sakethh.composeexercises.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sakethh.composeexercises.ui.theme.Colors

@Composable
fun Modifier.defaultContentModifier() = this
    .fillMaxSize()
    .background(Colors.Surface)
    .padding(
        start = 15.dp, end = 15.dp,

        // only because figma design clearly has bottom padding,
        // else `navigationBarsPadding()` should be sufficient
        bottom = 15.dp
    )
    .windowInsetsPadding(WindowInsets.statusBars)
    .navigationBarsPadding()