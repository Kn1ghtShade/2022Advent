package com.example.a2022advent

import java.io.File

private class Tree(val height: Int, var visible: Boolean, val x: Int, val y: Int)

class Problem8A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_8_input.txt"
    val columns = ArrayList<ArrayList<Tree>>()
    val rows = ArrayList<ArrayList<Tree>>()
    val trees = ArrayList<Tree>()
    var index = 0
    File(filename).forEachLine {
        if (columns.size == 0) for (i in it.indices) columns.add(ArrayList())
        rows.add(ArrayList())
        for (i in it.indices) {
            val t = Tree(it[i].digitToInt(), false, i, index)
            rows[index].add(t)
            columns[i].add(t)
            trees.add(t)
        }
        index++
    }
    for (tree in trees) {
        if (tree.x == 0 || tree.x == columns.size - 1 || tree.y == 0 || tree.y == rows.size - 1) {
            tree.visible = true
            continue
        }
        // check top
        if (checkVisible(0, tree.y, columns[tree.x], tree.height)) {
            // println("Tree ${tree.x}, ${tree.y}  seen from top!")
            tree.visible = true
        }
        // check bottom
        if (checkVisible(tree.y + 1, columns[0].size, columns[tree.x], tree.height)) {
            // println("Tree ${tree.x}, ${tree.y}  seen from bottom!")
            tree.visible = true
        }
        // check left
        if (checkVisible(0, tree.x, rows[tree.y], tree.height)) {
            // println("Tree ${tree.x}, ${tree.y}  seen from left!")
            tree.visible = true
        }
        // check right
        if (checkVisible(tree.x + 1, rows[0].size, rows[tree.y], tree.height)) {
            // println("Tree ${tree.x}, ${tree.y}  seen from right!")
            tree.visible = true
        }
    }
    var totalVisible = 0
    for (i in trees.indices) {
        if (trees[i].visible == true) {
            println(trees[i].visible)
            totalVisible++
        }
    }
    println("${totalVisible}")
}

private fun checkVisible(start: Int, end: Int, grid: ArrayList<Tree>, height: Int): Boolean {
    for (i in start until end) {
        // println("Grid Height: ${grid[i].height}   Tree Height: $height")
        if (grid[i].height >= height) {
            // println("Should be false")
            return false
        }
    }
    return true
}
