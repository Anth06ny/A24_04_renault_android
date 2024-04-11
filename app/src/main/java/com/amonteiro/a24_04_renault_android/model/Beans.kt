package com.amonteiro.a24_04_renault_android.model

import java.util.Random
import kotlin.concurrent.thread

fun main() {

    val random = RandomName()
    random+="Bobby"
    random-="tata"
    random put "John"
    repeat(10) {
        val (n1, n2) = random
        println("$n1 et $n2")
    }

    thread {

    }
}

interface ActionI {
    fun travail(task: String)
    fun pause()
}

class Responsable(val excutant: ActionI) : ActionI by excutant

class Ouvrier() : ActionI {
    override fun travail(task: String) {
        println("Ouvrier fait : $task")
    }

    override fun pause() {
        println("Ouvrier fait une pause")
    }
}

data class PictureBean(val id: Int, val url: String, val title: String, val longText: String)


class RandomName {
    private val list = arrayListOf("titi", "tata", "toto")
    private var oldValue = ""

    fun add(name: String?) = if (!name.isNullOrBlank() && name !in list) list.add(name) else false
    fun next() = list.random()

    fun nextDiff2() = list.filter { it != oldValue }.random().also { oldValue = it }

    fun next2() = Pair(nextDiff(), nextDiff())

    operator fun plusAssign(name: String) {
        add(name)
    }

    operator fun minusAssign(name:String) {
        list.remove(name)
    }

    operator fun component1() = nextDiff()
    operator fun component2() = nextDiff()

    infix fun put(name:String) = add(name)



    fun nextDiff(): String {
        var newValue = next()
        while (newValue == oldValue) {
            newValue = next()
        }
        oldValue = newValue

        return newValue
    }

}


class ThermometerBean(val min: Int, val max: Int, value: Int) {
    var value = value.coerceIn(min, max)
        set(newValue) {
            field = newValue.coerceIn(min, max)
        }

//    init {
//        this.value = value
//    }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)

        fun getFahrenheitThermometer() = ThermometerBean(20, 120, 32)
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