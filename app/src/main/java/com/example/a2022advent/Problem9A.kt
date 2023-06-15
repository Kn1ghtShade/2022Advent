package com.example.a2022advent

import java.io.File
import kotlin.math.abs

class Snake(var x: Int, var y: Int)

class Problem9A

private val head = Snake(0, 0)
private val tail = Snake(0, 0)
private var tailCoords = ArrayList<Snake>()

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_9_input.txt"
    File(filename).forEachLine {
        val command = it.split(' ')
        when (command[0]) {
            "L" -> for (i in 1..command[1].toInt()) moveLeft()
            "R" -> for (i in 1..command[1].toInt()) moveRight()
            "U" -> for (i in 1..command[1].toInt()) moveUp()
            "D" -> for (i in 1..command[1].toInt()) moveDown()
        }
    }
    println("${tailCoords.size}")
}

private fun moveLeft() {
    head.x--
    if (!checkTail()) {
        tail.x = head.x + 1
        tail.y = head.y
    }
    trackTail()
}

private fun moveRight() {
    head.x++
    if (!checkTail()) {
        tail.x = head.x - 1
        tail.y = head.y
    }
    trackTail()
}

private fun moveUp() {
    head.y++
    if (!checkTail()) {
        tail.x = head.x
        tail.y = head.y - 1
    }
    trackTail()
}

private fun moveDown() {
    head.y--
    if (!checkTail()) {
        tail.x = head.x
        tail.y = head.y + 1
    }
    trackTail()
}

private fun checkTail(): Boolean {
    return !(abs(head.x - tail.x) >= 2 || abs(head.y - tail.y) >= 2)
}

private fun trackTail() {
    for (snake in tailCoords) {
        if (snake.x == tail.x && snake.y == tail.y) return
    }
    tailCoords.add(Snake(tail.x, tail.y))
}
