package com.example.a2022advent

import java.io.File

class Problem4A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_4_input.txt"
    var elf1Start = 0
    var elf1End = 0
    var elf2Start = 0
    var elf2End = 0
    var sum = 0

    File(filename).forEachLine {
        var nums = it.split(",", "-")
        elf1Start = nums[0].toInt()
        elf1End = nums[1].toInt()
        elf2Start = nums[2].toInt()
        elf2End = nums[3].toInt()

        if (elf2End in elf1Start..elf1End && elf2Start >= elf1Start && elf2Start <= elf1End) {
            sum++
        } else if (elf1End in elf2Start..elf2End && elf1Start >= elf2Start && elf1Start <= elf2End) {
            sum++
        }
    }
    print("$sum")
}
