package com.sakethh.composeexercises.ui.account_setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.sakethh.composeexercises.R
import com.sakethh.composeexercises.ui.FintrackNavigation
import com.sakethh.composeexercises.ui.LocalNavController
import com.sakethh.composeexercises.ui.common.PrimaryButton
import com.sakethh.composeexercises.ui.common.defaultContentModifier
import com.sakethh.composeexercises.ui.onboarding.OnboardingIllustrations
import com.sakethh.composeexercises.ui.onboarding.addEdgeToEdgeScaffoldPadding
import com.sakethh.composeexercises.ui.theme.Colors
import com.sakethh.composeexercises.ui.theme.FintrackTheme

@Composable
fun GettingStartedScreen() {
    val navController = LocalNavController.current
    Scaffold(topBar = {
        Text(
            fontSize = 24.sp,
            text = buildAnnotatedString {
                val fintrackLabel = stringResource(id = R.string.fintrack_label)
                val welcomeMessage = stringResource(
                    id = R.string.gs_welcome_message,
                    fintrackLabel
                )

                val appNameStartIndex = welcomeMessage.indexOf(fintrackLabel)
                val appNameEndIndex = appNameStartIndex + fintrackLabel.length

                append(welcomeMessage)


                // @indexOf returns
                // > An index of the first occurrence of string or -1 if none is found.
                if (appNameStartIndex != -1) {
                    addStyle(
                        style = SpanStyle(color = Colors.Primary),
                        start = appNameStartIndex,
                        end = appNameEndIndex
                    )
                }
            },
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp,
                    top = 15.dp
                )
        )
    }, bottomBar = {
        Box(
            modifier = Modifier
                .padding(
                    bottom = 15.dp,
                    start = 15.dp,
                    end = 15.dp
                )
                .navigationBarsPadding()
        ) {
            PrimaryButton(
                text = stringResource(R.string.skip_btn_text),
                onClick = {}
            )
        }
    }, containerColor = Colors.Surface) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(5.dp)
                .defaultContentModifier()
                .addEdgeToEdgeScaffoldPadding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            GettingStartedItem(
                title = stringResource(R.string.gs_setup_a_pin_label),
                description = stringResource(R.string.gs_setup_a_pin_desc),
                imgVector = GettingStartedIllustrations.SetupAPin,
                onClick = {
                    navController.navigate(FintrackNavigation.PassCodeScreen) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

            GettingStartedItem(
                title = stringResource(R.string.gs_link_bank_accounts_label),
                description = stringResource(R.string.gs_link_bank_accounts_desc),
                imgVector = OnboardingIllustrations.Track,
                onClick = {}
            )

            GettingStartedItem(
                title = stringResource(R.string.gs_savings_label),
                description = stringResource(R.string.gs_savings_label_desc),
                imgVector = OnboardingIllustrations.Savings,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun GettingStartedScreenPreview() {
    FintrackTheme {
        val navController = rememberNavController()
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            GettingStartedScreen()
        }
    }
}

@Composable
private fun GettingStartedItem(
    title: String,
    description: String,
    imgVector: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            // approximated drop shadow from figma
            .dropShadow(
                shape = RoundedCornerShape(15.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    color = Color.Black.copy(0.1f)
                )
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.55f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 14.sp
            )
        }
        Image(
            imageVector = imgVector,
            modifier = Modifier.aspectRatio(1f),
            contentDescription = null
        )
    }
}