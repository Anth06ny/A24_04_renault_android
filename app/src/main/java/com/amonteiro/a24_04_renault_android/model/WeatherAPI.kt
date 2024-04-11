package com.amonteiro.a24_04_renault_android.model

import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
//    val res = WeatherAPI.loadWeather("Nice")
//    println("Il fait ${res.main.temp}° à ${res.name} avec un vent de ${res.wind.speed} m/s")

    val flow: Flow<WeatherBean> = WeatherAPI.getWeathers("Nice", "Toulouse", "Paris")
        .filter { it.wind.speed > 0 }


    runBlocking {
        flow.collect {
            println("Il fait ${it.main.temp}° à ${it.name} avec un vent de ${it.wind.speed} m/s")
        }
    }
}

object WeatherAPI {

    //Attribut instancié 1 seule fois car c'est un singleton
    //Et uniquement à la 1er utilisation (Lazy Loading)
    private val client = OkHttpClient()
    private val gson = Gson()

    const val URL_API = "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q="

    fun loadWeather(cityName: String): WeatherBean {
        //Requete
        val json: String = sendGet("$URL_API$cityName")
        println("json=$json")
        //parsing
        return gson.fromJson(json, WeatherBean::class.java)
    }

    fun getWeathers(vararg cities: String) = flow<WeatherBean> {
            cities.forEach {
                emit(WeatherAPI.loadWeather(it))
                delay(1000)
            }
    }

    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

data class WeatherBean(
    var name: String,
    var wind: WindBean,
    var main: DescriptionBean
)

data class WindBean(var speed: Double)
data class DescriptionBean(var temp: Double)
