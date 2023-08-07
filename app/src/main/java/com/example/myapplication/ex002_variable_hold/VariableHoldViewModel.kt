package com.example.myapplication.ex002_variable_hold

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel

class VariableHoldViewModel: ViewModel() {

    val count_mutableStateOf: MutableState<Int> = mutableStateOf(0)

//    val count_mutableStateOfAA by rememberSaveable(stateSaver = ) {
//
//    }//: MutableState<Int> = mutableStateOf(0)

}