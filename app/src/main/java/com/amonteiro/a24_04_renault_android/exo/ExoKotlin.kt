package com.amonteiro.a24_04_renault_android.exo

import com.amonteiro.a24_04_renault_android.PRICE_BAG
import com.amonteiro.a24_04_renault_android.PRICE_CROIS
import com.amonteiro.a24_04_renault_android.PRICE_SAND

fun main() {
    val (u1, u2, u3) = get3Users()
    println(u1)
    println(u2)
    println(u3)

    var i = 3
    var j = 3
    var k = 3




}

fun get3Users(): Triple<UserBean, UserBean, UserBean> {
    print("Donnez 3 nom : ")
    var (na1, na2, na3)  = readlnOrNull()!!.split(' ')
    print("Donnez 3 note : ")
    var (no1, no2, no3)  = readlnOrNull()!!.split(' ').map { it.toInt() }

    return Triple(UserBean(na1, no1), UserBean(na2, no2), UserBean(na3, no3))
}

class MyPair<out  T, U : Number?>(val first :T, var second :U)


fun test(lambda : (String) -> String) {

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



