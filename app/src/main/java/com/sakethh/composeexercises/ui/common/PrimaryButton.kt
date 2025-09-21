package com.sakethh.composeexercises.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sakethh.composeexercises.ui.onboarding.defaultButtonColors
import com.sakethh.composeexercises.ui.theme.Colors

@Composable
fun PrimaryButton(
    text: String, onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(25.dp),
        colors = defaultButtonColors(),
        modifier = Modifier
            .fillMaxWidth()
            .height(ButtonDefaults.MinHeight + 10.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = Colors.OnPrimary
        )
    }
}