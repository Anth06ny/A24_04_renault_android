package com.amonteiro.a24_04_renault_android.exo

import com.amonteiro.a24_04_renault_android.PRICE_BAG
import com.amonteiro.a24_04_renault_android.PRICE_CROIS
import com.amonteiro.a24_04_renault_android.PRICE_SAND

fun main() {

    var v2 : String? = "Toto"
    var v1 : String  = "String"


}

fun scanText(question: String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanTextV2(question: String) = print(question).let { readlnOrNull() ?: "-" }

fun scanNumber(question: String) = scanText(question).toIntOrNull() ?: 0


fun boulangerie(nbC: Int = 0, nbB: Int = 0, nbS: Int = 0) = nbC * PRICE_CROIS + nbS * PRICE_SAND + nbB * PRICE_BAG

fun myPrint(text: String) = println("#$text#")

fun pair(c: Int) = c % 2 == 0

fun min(a: Int, b: Int, c: Int): Int =
    if (a < b && a < c) a
    else if (b < a && b < c)
        b
    else
        c



