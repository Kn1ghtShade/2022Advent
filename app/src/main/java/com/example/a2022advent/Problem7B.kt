package com.example.a2022advent

import java.io.File

class Problem7B

private var min = Int.MAX_VALUE

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_7_input.txt"
    val root = ElfDir("/", null, ArrayList(), ArrayList())
    var ref = root
    File(filename).forEachLine {
        if (it[0] == '$') {
            if (it == "$ cd /") {
                ref = root
            } else if (it == "$ cd ..") {
                ref = ref.parent!!
            } else if (it != "$ ls") {
                val command = it.split(' ')
                for (dir in ref.dirs) {
                    if (dir.name == command[2]) {
                        ref = dir
                    }
                }
            }
        } else if (it.substring(0, 3) == "dir") {
            val command = it.split(' ')
            ref.dirs.add(ElfDir(command[1], ref, ArrayList(), ArrayList()))
        } else {
            val command = it.split(' ')
            ref.files.add(ElfFile(command[1], command[0].toInt()))
        }
    }
    val sum = getDirectorySize(root)
    val spaceNeeded = 30000000 - (70000000 - sum)
    getMinDirectory(spaceNeeded, root)
    println("$min")
}

private fun getDirectorySize(dir: ElfDir): Int {
    var sum = 0
    for (file in dir.files) {
        sum += file.size
    }
    for (d in dir.dirs) {
        val s = getDirectorySize(d)
        sum += s
    }
    return sum
}

private fun getMinDirectory(size: Int, ref: ElfDir): Int {
    var sum = 0
    for (file in ref.files) {
        sum += file.size
    }
    for (d in ref.dirs) {
        val s = getMinDirectory(size, d)
        if (s in (size + 1) until min) min = s
        sum += s
    }
    return sum
}
