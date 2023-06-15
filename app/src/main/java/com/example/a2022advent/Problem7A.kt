package com.example.a2022advent

import java.io.File

class ElfFile(val name: String, val size: Int)
class ElfDir(val name: String, var parent: ElfDir?, var dirs: ArrayList<ElfDir>, var files: ArrayList<ElfFile>) {
    fun getSize(): Int {
        var sum = 0
        for (file in files) {
            sum += file.size
        }
        for (dir in dirs) {
            sum += dir.getSize()
        }
        return sum
    }
}

class Problem7A

private var totalsum = 0

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
                var command = it.split(' ')
                for (dir in ref.dirs) {
                    if (dir.name == command[2]) {
                        ref = dir
                    }
                }
            }
        } else if (it.substring(0, 3) == "dir") {
            var command = it.split(' ')
            ref.dirs.add(ElfDir(command[1], ref, ArrayList(), ArrayList()))
        } else {
            var command = it.split(' ')
            ref.files.add(ElfFile(command[1], command[0].toInt()))
        }
    }
    var sum = getDirectorySize(root)
    if (sum < 100000) totalsum += sum
    println("$totalsum")
}

private fun getDirectorySize(dir: ElfDir): Int {
    var sum = 0
    for (file in dir.files) {
        sum += file.size
    }
    for (d in dir.dirs) {
        var s = getDirectorySize(d)
        if (s < 100000) totalsum += s
        sum += s
    }
    return sum
}
