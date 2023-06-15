package com.example.a2022advent

import java.io.File

class Problem3B

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_3_input.txt"
    var sum = 0
    var state = 0
    var ruck1 = ""
    var ruck2 = ""
    var ruck3 = ""
    File(filename).forEachLine {
        when (state) {
            0 -> ruck1 = it
            1 -> ruck2 = it
            2 -> ruck3 = it
        }
        if (state == 2) sum += evaluate(ruck1, ruck2, ruck3)
        state++
        if (state == 3) state = 0
    }
    println("$sum")
}

fun evaluate(ruck1: String, ruck2: String, ruck3: String): Int {
    var char = findCommonChar(ruck1, ruck2, ruck3)
    val priority = if (char.code in 65..90) char.code - 38 else char.code - 96
    return priority
}

private fun findCommonChar(ruck1: String, ruck2: String, ruck3: String): Char {
    var chars = ArrayList<Char>()
    for (char1 in ruck1) {
        for (char2 in ruck2) {
            if(char1 == char2) chars.add(char1)
        }
    }
    for (char3 in ruck3) {
        for (char in chars) {
            if (char == char3) return char
        }
    }
    return 'A'
}