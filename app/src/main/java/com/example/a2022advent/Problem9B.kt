package com.example.a2022advent

import java.io.File
import kotlin.math.abs
import kotlin.math.max

class SnakeSegment(var x: Int, var y: Int)

class Problem9B

private val segments = ArrayList<SnakeSegment>()
private var tailCoords = ArrayList<SnakeSegment>()

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_9_input.txt"
    for (i in 0..9) segments.add(SnakeSegment(0, 0))
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
    segments[0].x--
    for (i in 1..9) {
        if (!checkSegment(i)) {
            moveSegment(i)
        } else { break }
    }
    trackTail()
}

private fun moveRight() {
    segments[0].x++
    for (i in 1..9) {
        if (!checkSegment(i)) {
            moveSegment(i)
        } else { break }
    }
    trackTail()
}

private fun moveUp() {
    segments[0].y++
    for (i in 1..9) {
        if (!checkSegment(i)) {
            moveSegment(i)
        } else { break }
    }
    trackTail()
}

private fun moveDown() {
    segments[0].y--
    for (i in 1..9) {
        if (!checkSegment(i)) {
            moveSegment(i)
        } else { break }
    }
    trackTail()
}

private fun checkSegment(s: Int): Boolean {
    return max(abs(segments[s - 1].x - segments[s].x), abs(segments[s - 1].y - segments[s].y)) <= 1
}

private fun moveSegment(i: Int) {
    while (!checkSegment(i)) {
        if (segments[i].x == segments[i - 1].x) {
            segments[i].x = if (segments[i - 1].x > segments[i].x) segments[i].x + 1 else segments[i].x - 1
        } else if (segments[i].y == segments[i - 1].y) {
            segments[i].y = if (segments[i - 1].y > segments[i].y) segments[i].y + 1 else segments[i].y - 1
        } else {
            segments[i].x =
                if (segments[i - 1].x > segments[i].x) segments[i].x + 1 else segments[i].x - 1
            segments[i].y =
                if (segments[i - 1].y > segments[i].y) segments[i].y + 1 else segments[i].y - 1
        }
    }
}

private fun trackTail() {
    for (snake in tailCoords) {
        if (snake.x == segments[9].x && snake.y == segments[9].y) return
    }
    tailCoords.add(SnakeSegment(segments[9].x, segments[9].y))
}
