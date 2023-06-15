package com.example.a2022advent

import java.io.File

class Problem3A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_3_input.txt"
    var sum = 0
    File(filename).forEachLine {
        val comp1 = it.substring(0, it.length / 2)
        val comp2 = it.substring(it.length / 2, it.length)
        val char = findCommonChar(comp1, comp2)
        val priority = if (char.code in 65..90) char.code - 38 else char.code - 96
        sum += priority
    }
    println("$sum")
}

private fun findCommonChar(comp1: String, comp2: String): Char {
    for (char1 in comp1) {
        for (char2 in comp2) {
            if (char1 == char2) {
                return char1
            }
        }
    }
    return 'A'
}
