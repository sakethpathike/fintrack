package com.sakethh.composeexercises

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sakethh.composeexercises.ui.FintrackNavigation
import com.sakethh.composeexercises.ui.LocalNavController
import com.sakethh.composeexercises.ui.account_setup.GettingStartedScreen
import com.sakethh.composeexercises.ui.account_setup.passcode.PasscodeScreen
import com.sakethh.composeexercises.ui.theme.FintrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FintrackTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = FintrackNavigation.GettingStartedScreen
                    ) {
                        composable<FintrackNavigation.GettingStartedScreen> {
                            GettingStartedScreen()
                        }
                        composable<FintrackNavigation.PassCodeScreen> {
                            PasscodeScreen()
                        }
                    }
                }
            }
        }
    }
}

