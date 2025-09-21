package com.sakethh.composeexercises.ui

import kotlinx.serialization.Serializable

sealed interface FintrackNavigation {
    @Serializable
    data object GettingStartedScreen : FintrackNavigation

    @Serializable
    data object PassCodeScreen : FintrackNavigation
}