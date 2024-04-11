package com.amonteiro.a24_04_renault_android.exo

import java.util.Calendar


data class UserBean(var name: String, var old: Int)


fun main() {
    exo3()
}

fun createPairTime(): Pair<Long, Long> {
    val startOfDay = Calendar.getInstance()
    startOfDay.set(Calendar.MINUTE, 0)
    startOfDay.set(Calendar.HOUR_OF_DAY, 0)
    val time = startOfDay.timeInMillis / 1000

    val endOfDay = Calendar.getInstance()
    endOfDay.set(Calendar.MINUTE, 59)
    endOfDay.set(Calendar.HOUR_OF_DAY, 23)
    val timeEnd = endOfDay.timeInMillis / 1000

    return Pair(time, timeEnd)
}

fun createPairTime2() = Pair(
    Calendar.getInstance().apply {
        set(Calendar.MINUTE, 0)
        set(Calendar.HOUR_OF_DAY, 0)
    }.timeInMillis / 1000,
    Calendar.getInstance().apply {
        set(Calendar.MINUTE, 59)
        set(Calendar.HOUR_OF_DAY, 23)
    }.timeInMillis / 1000
)


inline fun myHighOrderFunction(p1: Int = 5, block: (Int) -> String = { "" }) {
    var v2 = p1 + 5

    block(v2)
}

data class PersonBean(var name: String, var note: Int)

fun exo3() {
    val list = arrayListOf(
        PersonBean("toto", 16),
        PersonBean("Tata", 20),
        PersonBean("Bou", 20),
        PersonBean("Titi", 20),
        PersonBean("Tata", 20),
        PersonBean("Toto", 8),
        PersonBean("Blabla", 8),
        PersonBean("Charles", 14)
    )

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >= 10 }.joinToString("\n") { (name, note) ->
        "-${name} : ${note}"

    }
    )

    val isToto: (PersonBean) -> Boolean = { it.name.equals("toto", true) }
    //TODO
    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    //println(list.count { it.name.equals("toto", true) })
    //println(list.count { isToto(it) })
    println(list.count(isToto))

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.count { isToto(it) && it.note >= 10 })

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val average = list.map { it.note }.average()
    println(list.count { isToto(it) && it.note >= average })

    println("\n\nAfficher la list triée par nom sans doublon")
    println(list.distinctBy { it.name }.sortedBy { it.name }.joinToString("\n") { "-${it.name} : ${it.note}" })

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.filter { it.note < 10 }.forEach { it.note++ }

    println("\n\nAjouter un point à tous les Toto")
    list.filter(isToto).forEach { it.note++ }

    println("\n\nCréer une nouvelle liste avec les toto qui ont un point de plus sans toucher aux Toto originels")
    val newList = list.filter(isToto).map { it.copy(note = it.note + 1) }

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    val minNote = list.minOfOrNull { it.note }
    list.removeIf { it.note == minNote }
    //list.minOfOrNull { it.note }.also { minNote ->  list.removeIf { it.note == minNote }}

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    println(list.filter { it.note > 10 }.sortedBy { it.name }.joinToString { it.name })

    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")

    val res = list.groupBy { it.note }.entries.sortedBy { it.key }.joinToString("\n") {
        "${it.key} : ${it.value.joinToString(" ") { it.name }}"
    }
    println("res=$res")


}

inline fun compareUsers(u1: UserBean, u2: UserBean, u3: UserBean, comparator: (UserBean, UserBean) -> UserBean) = comparator(comparator(u1, u2), u3)

fun compareUsersByNameV2(u1: UserBean, u2: UserBean): UserBean {
    return if (u1.name.lowercase() <= u2.name.lowercase()) u1 else u2
}

fun exo2() {
    val compareUsersByName: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }
    val compareUsersByOld: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.old > u2.old) u1 else u2 }

    val user1 = UserBean("Toto", 12)
    val user2 = UserBean("Tata", 10)
    println(compareUsersByName(user1, user2))
    println(compareUsersByOld(user1, user2))

//    var minToHour: ((Int?) -> Pair<Int, Int>?)? = { if (it == null) null else Pair(it / 60, it % 60) }
//
//    println(minToHour?.invoke(123))
//    println(minToHour?.invoke(null))
//    minToHour = null

    val u1 = UserBean("Bob", 19)
    val u2 = UserBean("Toto", 45)
    val u3 = UserBean("Charles", 26)
    println(
        compareUsers(u1, u2, u3, compareUsersByName)
    ) // UserBean(name=Bob old=19)
    println(compareUsers(u1, u2, u3, compareUsersByOld)) // UserBean(name=Toto old=45)

//    val near30 = compareUsers(u1, u2, u3, { user1, user2 -> } )
    val near30 = compareUsers(u1, u2, u3) { it1, it2 ->
        if (Math.abs(30 - it1.old) < Math.abs(30 - it2.old)) it1 else it2
    }

    println("near30=$near30")


    val list = arrayListOf(u1, u2, u3)

    for (i in list.indices) {
        list[i].old++
    }

    for (u in list) {
        u.old++
    }
}