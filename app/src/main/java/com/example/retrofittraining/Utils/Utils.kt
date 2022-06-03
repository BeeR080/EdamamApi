package com.example.retrofittraining.Utils

import android.text.TextUtils
import com.google.android.material.textfield.TextInputEditText
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
