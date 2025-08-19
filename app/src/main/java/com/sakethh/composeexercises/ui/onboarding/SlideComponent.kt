package com.sakethh.composeexercises.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SlideComponent(
    paddingValues: PaddingValues, imageVector: ImageVector, headline: String, description: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 15.dp, end = 15.dp,
                bottom = paddingValues.calculateBottomPadding(),
            )
            .navigationBarsPadding()
    ) {
        Text(
            text = headline,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            lineHeight = 38.4.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 14.sp,
            lineHeight = 21.sp
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    // in the figma design, it's clearly larger,
                    // so this "hacky" way works
                    .scale(1.12f),
            )
        }
    }
}