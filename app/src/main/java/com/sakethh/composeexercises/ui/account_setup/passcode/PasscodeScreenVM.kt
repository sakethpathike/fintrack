package com.sakethh.composeexercises.ui.account_setup.passcode

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PasscodeScreenVM : ViewModel() {

    // for more important info, SavedStateHandle would make a lot of sense,
    // since we could recover the info even after process death forced by the system
    // but for our passcode, this should be fine (for now)
    private val _passcode = mutableStateListOf<Char?>()
    val passcode: List<Char?> = _passcode

    companion object {
        const val PASSCODE_LENGTH = 4
    }

    // naming follows stack-style,
    // since we're literally implementing stack behavior here
    fun pushPasscodeDigit(digit: Char) {
        if (_passcode.size < PASSCODE_LENGTH) {
            _passcode.add(digit)
        }
    }

    fun popPasscodeDigit() {
        if (_passcode.isNotEmpty()) {
            _passcode.removeAt(_passcode.lastIndex)
        }
    }
}