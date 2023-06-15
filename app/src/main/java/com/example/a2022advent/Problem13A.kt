package com.example.a2022advent

import java.io.File
import java.util.LinkedList
import java.util.Queue

class Element(var isInt: Boolean, val num: Int, val list: ArrayList<Element>) {
    override fun toString(): String {
        return if (isInt) "$num" else "$list "
    }
}

class Problem13A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_13_input.txt"
    var state = 0
    var index = 1
    var sum = 0
    var left = ArrayList<Element>()
    var right = ArrayList<Element>()
    File(filename).forEachLine {
        when (state) {
            0 -> left = parseInput(it)
            1 -> right = parseInput(it)
            2 -> {
                if (checkElement(left, right) == 1) {
                    sum += index
                }
                state = -1
                index++
            }
        }
        state++
    }
    if (checkElement(left, right) == 1) {
        sum += index
    }
    println(sum)
}

// 0 = Equal, 1 = valid, 2 = invalid
private fun checkElement(left: ArrayList<Element>, right: ArrayList<Element>): Int {
    if (left.isEmpty() && right.isEmpty()) {
        return 0
    } else if (left.isEmpty()) {
        return 1
    } else if (right.isEmpty()) return 2
    for (e in left.indices) {
        if (e == right.size) return 2
        if (left[e].isInt && right[e].isInt) {
            if (left[e].num > right[e].num) return 2
            if (left[e].num < right[e].num) return 1
        } else {
            if (left[e].isInt) {
                left[e].isInt = false
                left[e].list.add(Element(true, left[e].num, ArrayList()))
            } else if (right[e].isInt) {
                right[e].isInt = false
                right[e].list.add(Element(true, right[e].num, ArrayList()))
            }
            var comp = checkElement(left[e].list, right[e].list)
            if (comp == 1 || comp == 2) return comp
        }
    }
    return if (right.size > left.size) {
        1
    } else {
        0
    }
}

private fun parseInput(s: String): ArrayList<Element> {
    val list = ArrayList<Element>()
    if (s.isEmpty()) return list
    val string = s.substring(1, s.length - 1)
    var startParse = 0
    for (i in string.indices) {
        if (i < startParse) continue
        if (string[i] == '[') {
            list.add(Element(false, -1, parseInput(string.substring(i, findMatchingBrace(i, string) + 1))))
            startParse = findMatchingBrace(i, string) + 1
        } else if (string[i] == ',' || string[i] == ']') {
            continue
        } else {
            var int = string[i].digitToInt()
            var n = 1
            while (i + n < string.length && string[i + n].digitToIntOrNull() != null) {
                int *= 10
                int += string[i + n].digitToInt()
                n++
            }
            list.add(Element(true, int, ArrayList()))
            startParse = i + n
        }
    }
    return list
}

private fun findMatchingBrace(index: Int, string: String): Int {
    var matchingBraces = 1
    for (i in index + 1 until string.length) {
        when (string[i]) {
            '[' -> matchingBraces++
            ']' -> matchingBraces--
        }
        if (matchingBraces == 0) return i
    }
    return -1
}
