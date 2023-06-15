package com.example.a2022advent

import java.io.File

class Problem10A

private var X = 1
private var clock = 0
private var totalSignal = 0

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_10_input.txt"
    File(filename).forEachLine {
        clock++
        if (clock == 20 || (clock - 20) % 40 == 0) calculateSignalStrength()
        if (it != "noop") {
            var command = it.split(' ')
            addx(command[1].toInt())
        }
    }
    println(totalSignal)
}

private fun addx(x: Int) {
    var c = clock + 2 - 1
    while (clock < c) {
        clock++
        if (clock == 20 || (clock - 20) % 40 == 0) calculateSignalStrength()
    }
    X += x
}

private fun calculateSignalStrength() {
    println("Clock Tick: $clock   Register: $X")
    totalSignal += clock * X
}
