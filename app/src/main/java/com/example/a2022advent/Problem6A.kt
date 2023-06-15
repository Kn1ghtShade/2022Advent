package com.example.a2022advent

import java.io.File

class Problem6A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_6_input.txt"
    File(filename).forEachLine {
        var index = 0
        while (!check(index, it)) {
            index++
        }
        println("${index + 4}")
    }
}

private fun check(index: Int, text: String): Boolean {
    for (i in 0..2) {
        for (j in i + 1..3) {
            if (text[index + i] == text[index + j]) {
                return false
            }
        }
    }
    return true
}