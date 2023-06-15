package com.example.a2022advent

import java.io.File
import java.util.Stack

class Problem5A

var stacks = ArrayList<Stack<Char>>()

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_5_input.txt"
    initialize(stacks)
    File(filename).forEachLine {
        val nums = it.split(' ')
        move(nums[1].toInt(), nums[3].toInt(), nums[5].toInt())
    }
    for (i in 0..8) {
        print(stacks[i].peek())
    }
}

private fun initialize(stacks: ArrayList<Stack<Char>>) {
    for (i in 0..8) {
        stacks.add(Stack<Char>())
    }
    stacks[0].addAll(listOf<Char>('N', 'D', 'M', 'Q', 'B', 'P', 'Z'))
    stacks[1].addAll(listOf<Char>('C', 'L', 'Z', 'Q', 'M', 'D', 'H', 'V'))
    stacks[2].addAll(listOf<Char>('Q', 'H', 'R', 'D', 'V', 'F', 'Z', 'G'))
    stacks[3].addAll(listOf<Char>('H', 'G', 'D', 'F', 'N'))
    stacks[4].addAll(listOf<Char>('N', 'F', 'Q'))
    stacks[5].addAll(listOf<Char>('D', 'Q', 'V', 'Z', 'F', 'B', 'T'))
    stacks[6].addAll(listOf<Char>('Q', 'M', 'T', 'Z', 'D', 'V', 'S', 'H'))
    stacks[7].addAll(listOf<Char>('M', 'G', 'F', 'P', 'N', 'Q'))
    stacks[8].addAll(listOf<Char>('B', 'W', 'R', 'M'))
}

private fun move(number: Int, startStack: Int, endStack: Int) {
    for (i in 1..number) {
        stacks[endStack - 1].add(stacks[startStack - 1].pop())
    }
}