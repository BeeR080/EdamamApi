package com.example.retrofittraining.Utils

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.RoundingMode
import java.text.DecimalFormat

fun DoubleRoudTo(nutrietns:Double): String {

    var nutrient = DecimalFormat("#.##")
    nutrient.roundingMode = RoundingMode.CEILING
    return  nutrient.format(nutrietns)
}

fun inputChek(name: String): Boolean {
    return !(TextUtils.isEmpty(name))

}

