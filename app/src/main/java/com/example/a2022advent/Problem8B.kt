package com.example.a2022advent

import java.io.File

private class ScenicTree(val height: Int, var scenicScore: Int, val x: Int, val y: Int)

class Problem8B

private val columns = ArrayList<ArrayList<ScenicTree>>()
private val rows = ArrayList<ArrayList<ScenicTree>>()

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_8_input.txt"
    val trees = ArrayList<ScenicTree>()
    var index = 0
    File(filename).forEachLine {
        if (columns.size == 0) for (i in it.indices) columns.add(ArrayList())
        rows.add(ArrayList())
        for (i in it.indices) {
            val t = ScenicTree(it[i].digitToInt(), 0, i, index)
            rows[index].add(t)
            columns[i].add(t)
            trees.add(t)
        }
        index++
    }
    var max = 0
    for (tree in trees) {
        val score = getScenicScore(tree)
        if (score > max) {
            max = score
            println("Tree ${tree.x}, ${tree.y}  has a scenic score of $score")
        }
    }
    println(max)
}

private fun getScenicScore(tree: ScenicTree): Int {
    var leftScore = 0
    var rightScore = 0
    var upScore = 0
    var downScore = 0
    // Check left
    for (i in tree.x - 1 downTo 0) {
        if (rows[tree.y][i].height >= tree.height) { leftScore++; break } else leftScore++
    }
    // Check right
    for (i in tree.x + 1 until rows[0].size) {
        if (rows[tree.y][i].height >= tree.height) { rightScore++; break } else rightScore++
    }
    // Check up
    for (i in tree.y - 1 downTo 0) {
        if (columns[tree.x][i].height >= tree.height) { upScore++; break } else upScore++
    }
    // Check down
    for (i in tree.y + 1 until columns[0].size) {
        if (columns[tree.x][i].height >= tree.height) { downScore++; break } else downScore++
    }
    return leftScore * rightScore * upScore * downScore
}
