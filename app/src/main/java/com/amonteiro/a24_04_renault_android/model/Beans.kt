package com.amonteiro.a24_04_renault_android.model

import java.util.Random

fun main() {
    val randomName = RandomName()
    randomName.add("bobby")
    repeat(10) {
        println(randomName.next() + " ")
    }

}

class RandomName {
    private val list = arrayListOf("titi", "tata", "toto")
    private var oldValue = ""

    fun add(name:String?)  = if(!name.isNullOrBlank() && name !in list) list.add(name) else false
    fun next() = list.random()

    fun nextDiff2() = list.filter { it != oldValue  }.random().also { oldValue = it }

    fun next2() = Pair(nextDiff(), nextDiff())


    fun nextDiff(): String {
       var newValue = next()
       while(newValue == oldValue){
           newValue = next()
       }
        oldValue = newValue

        return newValue
    }

}


class ThermometerBean(val min:Int, val max : Int, value : Int ) {
    var value = value.coerceIn(min, max)
        set(newValue) {
            field = newValue.coerceIn(min, max)
        }

//    init {
//        this.value = value
//    }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)

        fun getFahrenheitThermometer()  = ThermometerBean(20, 120,32)
    }
}

class PrintRandomIntBean(val max: Int) {
    private val random = Random()

    init {
        println("init")
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println("constructor")
        println(random.nextInt(max))
    }

}

class HouseBean(var color: String, width: Int, length: Int) {
    var area = width * length

    fun print() = println("color=$color area=$area")
}


data class CarBean(var marque: String = "", var model: String = "") {
    var color = ""
}