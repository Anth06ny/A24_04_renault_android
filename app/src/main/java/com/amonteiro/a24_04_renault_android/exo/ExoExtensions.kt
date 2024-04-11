package com.amonteiro.a24_04_renault_android.exo

import com.amonteiro.a24_04_renault_android.model.CarBean
import com.google.gson.Gson

fun main() {

    "Coucou".println()
    CarBean("Seat", "Leon").toString().println()

    val json: String = CarBean("Seat", "Leon").toJson()

    val user : UserBean? = null
    print(user.toJson()) // {}

}

fun CarBean.toblabal()  {
    marque = "bla"
}

fun String.println() = println(this)

fun Any?.toJson() = Gson().toJson(this ?: Unit)

fun String.kids(): String = this.mapIndexed { i, c -> if(i%2 == 0) c.uppercase() else c.lowercase()}.joinToString(separator = "")