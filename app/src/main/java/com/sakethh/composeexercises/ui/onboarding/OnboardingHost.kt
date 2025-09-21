package com.sakethh.composeexercises.ui.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakethh.composeexercises.ui.common.PrimaryButton
import com.sakethh.composeexercises.ui.theme.Colors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingHost(modifier: Modifier = Modifier) {
    val onBoardingPagerState = rememberPagerState(pageCount = { 3 })
    var showCreateAccountSheet by rememberSaveable {
        mutableStateOf(false)
    }

    val createAccountSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(containerColor = Color.Transparent, topBar = {
        Row(
            modifier = Modifier
                .padding(
                    start = 10.dp, end = 10.dp, top = 14.dp, bottom = 24.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(times = 3) {
                val backgroundColor by animateColorAsState(
                    targetValue = if (onBoardingPagerState.currentPage == it) Color(0xFF161B26) else Color(
                        0xFF94969C
                    ), animationSpec = tween(durationMillis = 300)
                )
                Box(
                    modifier = Modifier
                        .padding(end = 5.dp, start = 5.dp)
                        .height(6.04.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(2.5.dp))
                        .background(backgroundColor)
                )
            }
        }
    }, modifier = modifier, bottomBar = {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .navigationBarsPadding()
        ) {
            PrimaryButton(text = "Create an account", onClick = {
                showCreateAccountSheet = true
                coroutineScope.launch {
                    createAccountSheetState.show()
                }
            })
            Spacer(Modifier.height(15.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFF161B26)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        style = MaterialTheme.typography.titleSmall,
                        text = "Sign in",
                        color = Color(0xFF008080),
                        modifier = Modifier.clickable {
                            // navigate to sign in screen
                        },
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }) { paddingValues ->
        HorizontalPager(
            modifier = Modifier
                .addEdgeToEdgeScaffoldPadding(paddingValues)
                .fillMaxSize(),
            state = onBoardingPagerState
        ) {
            // instead of this
            // we can also create a list (based on a data class) and show the content based on index
            // but that's not really needed in this case as we have 3 fixed slides
            when (it) {
                0 -> SlideComponent(
                    paddingValues = paddingValues,
                    imageVector = OnboardingIllustrations.Track,
                    headline = "Track Your\nExpenses",
                    description = "Get insights into where your money goes and make informed financial decisions."
                )

                1 -> SlideComponent(
                    paddingValues = paddingValues,
                    imageVector = OnboardingIllustrations.Savings,
                    headline = "Set Savings\nGoals",
                    description = "Whether it’s for a vacation, a new car, or an emergency fund, we help you stay on track."
                )

                2 -> SlideComponent(
                    paddingValues = paddingValues,
                    imageVector = OnboardingIllustrations.Insights,
                    headline = "Get Financial\nInsights",
                    description = "Access detailed reports and analytics to make better financial choices."
                )
            }
        }
    }

    if (showCreateAccountSheet) {
        ModalBottomSheet(
            containerColor = Color.White,
            dragHandle = {},
            sheetState = createAccountSheetState,
            onDismissRequest = {
                coroutineScope.launch {
                    createAccountSheetState.hide()
                }.invokeOnCompletion {
                    showCreateAccountSheet = false
                }
            }) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(
                        text = "Create an account",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 20.sp
                    )
                    IconButton(onClick = {
                        coroutineScope.launch {
                            createAccountSheetState.hide()
                        }.invokeOnCompletion {
                            showCreateAccountSheet = false
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
                Text(
                    text = buildAnnotatedString {
                        append("By pressing accept below you agree to our ")

                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            withLink(LinkAnnotation.Url(url = "https://www.skool.com/android-devs/about")) {
                                append("Terms and Conditions")
                            }
                        }

                        append(". You can find out more about how we use your data in our ")

                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            withLink(LinkAnnotation.Url(url = "https://www.skool.com/android-devs/about")) {
                                append("Privacy Policy")
                            }
                        }
                        append(".")
                    },
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 16.sp,
                    lineHeight = 19.2.sp,
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                )
                PrimaryButton(text = "Accept and Continue", onClick = {})
            }
        }
    }
}


// keeps the system nav bar transparent while still applying padding on top, end, and bottom when working with Scaffold(s);
// kinda a hacky workaround, but there doesn't seem to be clear documentation on how to handle this properly
fun Modifier.addEdgeToEdgeScaffoldPadding(paddingValues: PaddingValues) = this
    .padding(
        top = paddingValues.calculateTopPadding(), start = paddingValues.calculateStartPadding(
            LayoutDirection.Ltr
        ), end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)
    )
    .consumeWindowInsets(paddingValues)


@Composable
fun defaultButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors().copy(containerColor = Colors.Primary)
}