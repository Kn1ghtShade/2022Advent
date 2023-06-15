package com.example.a2022advent

import java.io.File

class Element(val isInt: Boolean, val num: Int, val list: ArrayList<Element>)

class Problem13A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_13_input.txt"
    var state = 0
    var left = ArrayList<Element>()
    var right = ArrayList<Element>()
    File(filename).forEachLine {
        when (state) {
            0 -> left = parseInput(it)
            1 -> right = parseInput(it)
            2 -> {
                checkInput(left, right)
                state = 0
            }
        }
    }
}

private fun checkInput(left: ArrayList<Element>, right: ArrayList<Element>): Boolean {
    if (left.size == 0) return false
    if (right.size == 0) return true
    for (e in left.indices) {
        if (e == right.size) return true
        if (left[e].isInt && right[e].isInt) {
            if (left[e].num < right[e].num) return true
            if (left[e].num > right[e].num) return false
        }
    }
    return false
}

private fun parseInput(s: String): ArrayList<Element> {
    val list = ArrayList<Element>()
    if (s.isEmpty()) return list
    val string = s.substring(1, s.length - 1)
    for (i in string.indices) {
        if (string[i] == '[') {
            list.add(Element(false, -1, parseInput(string.substring(i, findMatchingBrace(i, string) + 1))))
        } else if (string[i] == ',' || string[i] == ']') {
            continue
        } else {
            list.add(Element(true, string[i].digitToInt(), ArrayList()))
        }
    }
    return list
}

private fun findMatchingBrace(index: Int, string: String): Int {
    var matchingBraces = 1
    for(i in index + 1 until string.length) {
        when (string[i]) {
            '[' -> matchingBraces++
            ']' -> matchingBraces--
        }
        if (matchingBraces == 0) return i
    }
    return -1
}
