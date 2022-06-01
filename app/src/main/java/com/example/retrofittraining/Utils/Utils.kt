package com.example.retrofittraining.Utils

import java.math.RoundingMode
import java.text.DecimalFormat

fun DoubleRoudTo(nutrietns:Double): String {

    var nutrient = DecimalFormat("#.##")
    nutrient.roundingMode = RoundingMode.CEILING
    return  nutrient.format(nutrietns)
}