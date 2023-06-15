package com.example.a2022advent

import java.io.File
import java.util.LinkedList
import java.util.Queue
import kotlin.collections.ArrayList

class Monkey(val items: Queue<Int>, var opType: Int, var opVar: Int, var divBy: Int, var onTrue: Int, var onFalse: Int) {
    var business = 0
    fun throwItems() {
        while (items.size > 0) {
            var item = items.remove()
            when (opType) {
                0 -> item += opVar
                1 -> item *= opVar
                2 -> item *= item
            }
            item /= 3
            if (item % divBy == 0) {
                monkeys[onTrue].catchItem(item)
            } else {
                monkeys[onFalse].catchItem(item)
            }
            business++
        }
    }

    fun catchItem(item: Int) {
        items.add(item)
    }
}

class Problem11A

private val monkeys = ArrayList<Monkey>()

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_11_input.txt"
    val input = File(filename).readLines()
    var cursor = 0
    while (cursor < input.size) {
        if (input[cursor].split(' ')[0] == "Monkey") {
            var monkey = Monkey(LinkedList(), 0, 0, 0, 0, 0)
            // Parse Items
            for (item in input[cursor + 1].split(' ', ',')) {
                if (item.toIntOrNull() != null) monkey.catchItem(item.toInt())
            }
            // Parse operation
            if (input[cursor + 2].contains('+')) {
                monkey.opVar = input[cursor + 2].last().digitToInt()
            } else {
                if (input[cursor + 2].split(' ').last() == "old") {
                    monkey.opType = 2
                } else {
                    monkey.opType = 1
                    monkey.opVar = input[cursor + 2].split(' ').last().toInt()
                }
            }
            // Parse Test
            monkey.divBy = input[cursor + 3].split(' ').last().toInt()
            monkey.onTrue = input[cursor + 4].split(' ').last().toInt()
            monkey.onFalse = input[cursor + 5].split(' ').last().toInt()
            monkeys.add(monkey)
        }
        cursor += 7
    }
    for (i in 1..20) {
        for (monkey in monkeys) monkey.throwItems()
    }
    println(checkBusiness())
}

private fun checkBusiness(): Int {
    val monkeyBusiness = ArrayList<Int>()
    for (monkey in monkeys) monkeyBusiness.add(monkey.business)
    monkeyBusiness.sort()
    monkeyBusiness.reverse()
    return (monkeyBusiness[0] * monkeyBusiness[1])
}
