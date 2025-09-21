package com.sakethh.composeexercises.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("LocalNavController isn't provided")
}