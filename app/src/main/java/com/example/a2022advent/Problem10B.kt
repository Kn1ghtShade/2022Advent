package com.example.a2022advent

import java.io.File
import kotlin.math.abs

class Problem10B

private var X = 1
private var clock = 0
private var totalSignal = 0

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_10_input.txt"
    File(filename).forEachLine {
        clock++
        drawPixel()
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
        drawPixel()
    }
    X += x
}

private fun drawPixel() {
    if (abs(((clock % 40) - 1) - X) <= 1) {
        print("#")
    } else {
        print(".")
    }
    if(clock % 40 == 0) println()
}
