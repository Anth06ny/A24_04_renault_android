package com.amonteiro.a24_04_renault_android

fun main() {

    val myLiveData = MyLiveData("départ")

    myLiveData.data = "2eme valeur"
    myLiveData.observator = {
        println(it)
    }

    myLiveData.data = "3eme valeur"

    myLiveData.observator = null


}


class MyLiveData(data:String?) {

    var data : String? = data
        set(value) {
            field = value
            observator?.invoke(value)
        }

    var observator : ((String?)->Unit)? = null


}