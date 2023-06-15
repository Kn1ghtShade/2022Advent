package com.example.a2022advent

import java.io.File

class Problem2A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_2_input.txt"
    var score = 0
    File(filename).forEachLine {
        var opponent = it[0]
        var self = it[2]

        when (self) {
            'X' -> score += 1
            'Y' -> score += 2
            'Z' -> score += 3
        }
        score += calculateWin(opponent, self)
    }
    println("$score")
}

private fun calculateWin(opponent: Char, self: Char): Int {
    var score = 0
    when (opponent.toString() + self.toString()) {
        "AX" -> score = 3
        "AY" -> score = 6
        "AZ" -> score = 0
        "BX" -> score = 0
        "BY" -> score = 3
        "BZ" -> score = 6
        "CX" -> score = 6
        "CY" -> score = 0
        "CZ" -> score = 3
    }
    return score
}
