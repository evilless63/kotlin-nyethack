package com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open val dangerlevel = 5
    fun description() = "Room: $name\n" +
            "Danger level: $dangerlevel"
    open fun load() = "Nothing much to see here..."
}

class TownSquare : Room("Town Square") {
    override val dangerlevel: Int
        get() = super.dangerlevel - 3
    override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"

    private val bellSound: String = "GWONG"
    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}