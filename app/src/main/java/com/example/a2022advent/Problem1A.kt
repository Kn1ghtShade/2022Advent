package com.example.a2022advent

import java.io.File

class Problem1A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_1A_input.txt"
    var maxSum = 0
    var curSum = 0
    File(filename).forEachLine {
        if (it.toIntOrNull() != null) {
            curSum += it.toInt()
        } else {
            maxSum = if(curSum > maxSum) curSum else maxSum
            curSum = 0
        }
    }
    println("$maxSum")
}
