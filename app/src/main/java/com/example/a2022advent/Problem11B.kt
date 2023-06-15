package com.example.a2022advent

import java.io.File
import java.util.LinkedList
import java.util.Queue
import kotlin.collections.ArrayList

class MonkeyLong(val items: Queue<ULong>, var opType: Int, var opVar: UInt, var divBy: UInt, var onTrue: Int, var onFalse: Int) {
    var business = 0
    fun throwItems() {
        while (items.size > 0) {
            var item = items.remove()
            when (opType) {
                0 -> item += opVar
                1 -> item *= opVar
                2 -> item *= item
            }
            item %= commonDivisor
            if (item % divBy == 0UL) {
                monkeyLongs[onTrue].catchItem(item)
            } else {
                monkeyLongs[onFalse].catchItem(item)
            }
            business++
        }
    }

    fun catchItem(item: ULong) {
        items.add(item)
    }
}

class Problem11B

val monkeyLongs = ArrayList<MonkeyLong>()
var commonDivisor = 1UL

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_11_input.txt"
    val input = File(filename).readLines()
    var cursor = 0
    while (cursor < input.size) {
        if (input[cursor].split(' ')[0] == "Monkey") {
            var monkey = MonkeyLong(LinkedList(), 0, 0U, 0U, 0, 0)
            // Parse Items
            for (item in input[cursor + 1].split(' ', ',')) {
                if (item.toIntOrNull() != null) monkey.catchItem(item.toULong())
            }
            // Parse operation
            if (input[cursor + 2].contains('+')) {
                monkey.opVar = input[cursor + 2].last().digitToInt().toUInt()
            } else {
                if (input[cursor + 2].split(' ').last() == "old") {
                    monkey.opType = 2
                } else {
                    monkey.opType = 1
                    monkey.opVar = input[cursor + 2].split(' ').last().toInt().toUInt()
                }
            }
            // Parse Test
            monkey.divBy = input[cursor + 3].split(' ').last().toInt().toUInt()
            monkey.onTrue = input[cursor + 4].split(' ').last().toInt()
            monkey.onFalse = input[cursor + 5].split(' ').last().toInt()
            monkeyLongs.add(monkey)
        }
        cursor += 7
    }
    for (monkey in monkeyLongs) commonDivisor *= monkey.divBy
    for (i in 1..10000) {
        for (monkey in monkeyLongs) monkey.throwItems()
    }
    println(checkBusiness())
}

private fun checkBusiness(): ULong {
    val monkeyBusiness = ArrayList<ULong>()
    for (monkey in monkeyLongs) monkeyBusiness.add(monkey.business.toULong())
    monkeyBusiness.sort()
    monkeyBusiness.reverse()
    println(monkeyBusiness)
    return (monkeyBusiness[0] * monkeyBusiness[1])
}
