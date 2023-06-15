package com.example.a2022advent

import java.io.File

class Vector(var x: Int, var y: Int, val height: Int = 0, var distance: Int = Int.MAX_VALUE)
class Grid(val nodes: ArrayList<Vector>) {
    fun getHeight(x: Int, y: Int): Int {
        for (vector in nodes) {
            if (vector.x == x && vector.y == y) return vector.height
        }
        return -1
    }
    fun getVector(x: Int, y: Int): Vector {
        for (vector in nodes) {
            if (vector.x == x && vector.y == y) return vector
        }
        return Vector(-1, -1)
    }
    fun getDistance(x: Int, y: Int): Int {
        for (vector in nodes) {
            if (vector.x == x && vector.y == y) return vector.distance
        }
        return -1
    }
    fun addPoint(x: Int, y: Int, height: Int) {
        nodes.add(Vector(x, y, height))
    }
}

private val grid = Grid(ArrayList())
private var start = Vector(0, 0, 1)
private var end = Vector(0, 0, 26)
private var gridLength = 0
private var gridHeight = 0
private var min = -1

class Problem12A

fun main() {
    val filename = "/Users/cecilia.schmitz/AndroidStudioProjects/2022Advent/app/src/main/java/com/example/a2022advent/advent_12_input.txt"
    var index = 0
    File(filename).forEachLine {
        for (i in it.indices) {
            if (it[i].code >= 97) {
                grid.addPoint(i, index, it[i].code - 96)
            } else if (it[i].code == 83) {
                start.x = i
                start.y = index
                grid.addPoint(i, index, 1)
            } else {
                end.x = i
                end.y = index
                grid.addPoint(i, index, 26)
            }
        }
        gridLength = it.length
        index++
    }
    gridHeight = index
    findShortestPath(grid.getVector(end.x, end.y), 0)
    println(grid.getDistance(start.x, start.y))
}

private fun findShortestPath(s: Vector, distance: Int) {
    s.distance = distance
    println("Node ${s.x}, ${s.y} set to distance of $distance")
    // Check left
    if (s.x != 0 && grid.getHeight(s.x - 1, s.y) >= s.height - 1 && grid.getDistance(s.x - 1, s.y) > s.distance + 1) {
        findShortestPath(grid.getVector(s.x - 1, s.y), distance + 1)
    }
    // Check right
    if (s.x != gridLength - 1 && grid.getHeight(s.x + 1, s.y) >= s.height - 1 && grid.getDistance(s.x + 1, s.y) > s.distance + 1) {
        findShortestPath(grid.getVector(s.x + 1, s.y), distance + 1)
    }
    // Check up
    if (s.y != 0 && grid.getHeight(s.x, s.y - 1) >= s.height - 1 && grid.getDistance(s.x, s.y - 1) > s.distance + 1) {
        findShortestPath(grid.getVector(s.x, s.y - 1), distance + 1)
    }
    // Check down
    if (s.y != gridHeight - 1 && grid.getHeight(s.x, s.y + 1) >= s.height - 1 && grid.getDistance(s.x, s.y + 1) > s.distance + 1) {
        findShortestPath(grid.getVector(s.x, s.y + 1), distance + 1)
    }
}

private fun contains(visited: ArrayList<Vector>, v: Vector): Boolean {
    for (vector in visited) if (vector.x == v.x && vector.y == v.y) return true
    return false
}

private fun minNonNegative(l: Int, r: Int, u: Int, d: Int): Int {
    var list = ArrayList<Int>()
    list.add(l)
    list.add(r)
    list.add(u)
    list.add(d)
    list.sort()
    var m = -1
    for (i in list) if (i != -1) { m = i; break; }
    if (min == -1 || m < min) min = m
    return m
}

private fun copy(array: ArrayList<Vector>): ArrayList<Vector> {
    val new = ArrayList<Vector>()
    for (vector in array) new.add(vector)
    return new
}
