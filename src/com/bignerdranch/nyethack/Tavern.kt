package com.bignerdranch.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
val patronGold = mutableMapOf<String, Double>()
fun main() {

    menuList.forEachIndexed { index, data ->
        println("$index: $data")
    }

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.shuffled().first(),
                menuList.shuffled().first())
        orderCount++
    }

//    com.bignerdranch.nyethack.displayPatronBalances()

}

fun performPurchase(price: Double, patronName: String): Boolean {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price

    if (totalPurse - price <= 0) {
        patronGold.remove(patronName)
        uniquePatrons.remove(patronName)
        return true
    }

    return false
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")
    val (type, name, price) = menuData.split(',')

    val vygnat = performPurchase(price.toDouble(), patronName)
    when (vygnat) {
         true -> {
             println("$patronName no money and poshel hah")
        }
        else -> {
            println("$patronName buys a $name ($type) for $price")
        }
    }

}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}