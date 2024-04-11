package com.amonteiro.a24_04_renault_android.exo

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.random.Random.Default.nextInt
import kotlin.random.Random.Default.nextLong

fun main() {
    //exempleCoroutine(5)
    electionResult()
}

fun electionResult() {
    val start = System.currentTimeMillis()
    //Pour stocker les async
    val task = ArrayList<Pair<ArrayList<Deferred<ResultBean?>>, Job>>()

    runBlocking {
        repeat(100) { numDept ->
            val listSameDept = ArrayList<Deferred<ResultBean?>>()
            val job = Job()

            repeat(10) {
                listSameDept += async(job) {
                    try {
                        getResultFromDepartment(it)
                    }
                    catch (e: Exception) {
                        delay(5000)
                        null
                    }
                }
            }
            task += Pair(listSameDept, job)
        }

        var nbGandalf = 0
        var nbDumbledore = 0
        var nbMerlin = 0

        task.mapNotNull {pairListSameDept ->
            select<ResultBean?> {
                pairListSameDept.first.forEach { it.onAwait{it} }
            }.also {
                pairListSameDept.second.cancel()
            }
        }.forEach {
            nbGandalf += it.nbVoteGandalf
            nbDumbledore += it.nbVoteDumbledore
            nbMerlin += it.nbVoteMerlin
        }

        var sum = nbGandalf + nbDumbledore + nbMerlin

        println("Gandalf : ${(nbGandalf * 100.0 / sum).format(2)}%")
        println("Dumbledore : ${(nbDumbledore * 100.0 / sum).format(2)}%")
        println("Merlin : ${(nbMerlin * 100.0 / sum).format(2)}%")

        println("Réalisée en ${(System.currentTimeMillis() - start)}ms")

    }
    println("Done in ${(System.currentTimeMillis() - start)}ms")
}

//Pour afficher avec 2 chiffres après la virgule 12.556.format(2)
fun Double.format(digits: Int) = "%.${digits}f".format(this)


//Récupère les résultats du département e
suspend fun getResultFromDepartment(deprtNumber: Int): ResultBean {
    delay(nextLong(3000))
    if (nextInt(10) == 1) {
        println("Erreur  : $deprtNumber")
        throw TimeoutException("404 sur le département $deprtNumber")
    }
    ///println("Le département : $deprtNumber a répondu")
    return ResultBean()
}

class ResultBean {
    val nbVoteGandalf = nextInt(10000)
    val nbVoteDumbledore = nextInt(10000)
    val nbVoteMerlin = nextInt(10000)
}

fun exempleCoroutine(callNumber: Int) {
    val start = System.currentTimeMillis()
    val box = BallotBoxBean()

    runBlocking {
        repeat(callNumber) {
            launch {
                box.add1VoiceAndWaitWithDelay()
            }
        }
    }


    println("Number : " + box.current)
    println("Done in ${(System.currentTimeMillis() - start) / 1000} seconds")
}


fun exempleThread() {
    val ballot = BallotBoxBean()
    val before = System.currentTimeMillis()

    val list = ArrayList<Thread>()

    repeat(100_000) {
        list += thread {
            ballot.add1VoiceAndWait()
        }
    }

    list.forEach { it.join() }

    println("number=${ballot.current}")
    val after = System.currentTimeMillis()
    println("Done in ${after - before} ms")
}

//Classe garantissant un compte ThreadSafe
class BallotBoxBean {
    private val number = AtomicInteger(0)

    suspend fun add1VoiceAndWaitWithDelay() {
        delay(2000)
        number.incrementAndGet()
    }

    fun add1VoiceAndWait() {
        Thread.sleep(2000)
        number.incrementAndGet()
    }

    val current
        get() = number.get()
}