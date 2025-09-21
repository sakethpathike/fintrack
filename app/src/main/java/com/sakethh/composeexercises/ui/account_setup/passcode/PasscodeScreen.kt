package com.sakethh.composeexercises.ui.account_setup.passcode

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sakethh.composeexercises.R
import com.sakethh.composeexercises.ui.LocalNavController
import com.sakethh.composeexercises.ui.common.PrimaryButton
import com.sakethh.composeexercises.ui.common.defaultContentModifier
import com.sakethh.composeexercises.ui.theme.FintrackTheme

@Composable
fun PasscodeScreen() {
    val navController = LocalNavController.current
    val passcodeScreenVM: PasscodeScreenVM = viewModel()
    Column(
        modifier = Modifier.defaultContentModifier()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        navController.navigateUp()
                    }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.ps_create_passcode_label),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(R.string.ps_create_passcode_desc),
                style = MaterialTheme.typography.titleSmall,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(PasscodeScreenVM.PASSCODE_LENGTH) {
                    PasscodeDigitBox(
                        value = passcodeScreenVM.passcode.getOrNull(it)
                    )
                }
            }
            var nextInt = rememberSaveable {
                1
            }
            Spacer(modifier = Modifier.height(50.dp))

            // maybe copy/pasting the
            // component(s) 3 times wouldn't be
            // that bad instead of this, but this should be fine too
            repeat(3) {
                PassCodeRow {
                    val rowNumeric1 = rememberSaveable {
                        (it + nextInt).digitToChar()
                    }
                    PasscodeDigit(
                        value = rowNumeric1,
                        onClick = {
                            passcodeScreenVM.pushPasscodeDigit(rowNumeric1)
                    })

                    val rowNumeric2 = rememberSaveable {
                        (it + ++nextInt).digitToChar()
                    }
                    PasscodeDigit(
                        value = rowNumeric2,
                        onClick = {
                            passcodeScreenVM.pushPasscodeDigit(rowNumeric2)
                    })

                    val rowNumeric3 = rememberSaveable {
                        (it + ++nextInt).digitToChar()
                    }
                    PasscodeDigit(
                        value = rowNumeric3,
                        onClick = {
                            passcodeScreenVM.pushPasscodeDigit(rowNumeric3)
                    })
                }
            }
            PassCodeRow {
                Text(
                    style = MaterialTheme.typography.titleSmall,
                    text = ".",
                    fontSize = 28.sp,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            passcodeScreenVM.pushPasscodeDigit('.')
                        },
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }),
                    textAlign = TextAlign.Center
                )

                PasscodeDigit(
                    value = '0',
                    onClick = {
                        passcodeScreenVM.pushPasscodeDigit('0')
                    }
                )

                Icon(
                    imageVector = PasscodeScreenVectors.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(25.dp))
                        .clickable(onClick = {
                            passcodeScreenVM.popPasscodeDigit()
                        },
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        })
                )
            }
        }
        PrimaryButton(
            text = stringResource(R.string.create_pin_btn_text),
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun PasscodeScreenPreview() {
    FintrackTheme {
        val navController = rememberNavController()
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            PasscodeScreen()
        }
    }
}

@Composable
private fun PassCodeRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        content = content
    )
}

@Composable
private fun RowScope.PasscodeDigit(
    value: Char, onClick: () -> Unit
) {
    // capriola font isn't applied to these digits in figma,
    // but we're gonna keep it, just for consistency
    Text(
        style = MaterialTheme.typography.titleSmall,
        text = value.toString(),
        fontSize = 28.sp,
        modifier = Modifier
            // figma design clearly doesn't
            // have padding at `start` and `end`,
            // but i'm including it since it looks better
            // than stretching the component fully
            .padding(2.5.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
            .background(color = Color.LightGray.copy(0.175f))
            .padding(top = 10.dp, bottom = 10.dp)
            .weight(1f),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun PasscodeDigitBox(value: Char?) {
    Box(
        modifier = Modifier
            .requiredSize(65.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(
                shape = RoundedCornerShape(5.dp),
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(0.5f)
            )
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        val digitColor = animateColorAsState(
            targetValue = if (value == null)
                Color.Black.copy(0.2f)
            else Color.Black,
        )
        Text(
            fontSize = 30.sp,
            text = rememberSaveable(value) {
                (value ?: 0).toString()
            },
            style = MaterialTheme.typography.titleSmall,
            color = digitColor.value
        )
    }
}