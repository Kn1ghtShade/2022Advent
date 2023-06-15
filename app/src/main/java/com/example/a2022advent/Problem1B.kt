package com.example.a2022advent

import java.io.File

class Problem1B

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_1A_input.txt"
    var curSum = 0
    val elves = ArrayList<Int>()
    File(filename).forEachLine {
        if (it.toIntOrNull() != null) {
            curSum += it.toInt()
        } else {
            elves.add(curSum)
            curSum = 0
        }
    }
    elves.sort()
    elves.reverse()
    println("${elves[0] + elves[1] + elves[2]}")
}
