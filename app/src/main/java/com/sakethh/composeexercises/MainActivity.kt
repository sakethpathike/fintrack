package com.sakethh.composeexercises

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import com.sakethh.composeexercises.ui.onboarding.OnboardingHost
import com.sakethh.composeexercises.ui.theme.ComposeExercisesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExercisesTheme {
                OnboardingHost(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars))
            }
        }
    }
}