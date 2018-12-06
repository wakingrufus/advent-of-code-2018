package com.github.wakingrufus.aoc

import kotlin.math.absoluteValue

data class Coordinate(val x: Int, val y: Int)

class Day6 {
    fun part1(input: List<String>): Int {
        return findLargestArea(input.map { parseCoordinate(it) })
    }

    fun findLargestArea(coordinates: List<Coordinate>): Int {
        val xMax = coordinates.maxBy { it.x }?.x ?: 1
        val xMin = coordinates.minBy { it.x }?.x ?: 0
        val yMax = coordinates.maxBy { it.y }?.y ?: 1
        val yMin = coordinates.minBy { it.y }?.y ?: 0
        return xMin.rangeTo(xMax)
                .flatMap { x ->
                    yMin.rangeTo(yMax)
                            .map { y ->
                                findClosestCoordinate(coordinates, x, y)
                            }
                }
                .groupBy { it }
                .maxBy { it.value.size }?.value?.size ?: 0
    }


    fun findClosestCoordinate(coordinates: List<Coordinate>, x: Int, y: Int): Coordinate? {
        val allDistances = coordinates.map {
            it to (it.x - x).absoluteValue + (it.y - y).absoluteValue
        }
        val min = allDistances.minBy { it.second }
        return if (allDistances.count { it.second == min?.second } > 1) null else min?.first
    }


    fun parseCoordinate(line: String): Coordinate {
        return line.split(",")
                .map(String::trim)
                .map(String::toInt)
                .let {
                    Coordinate(x = it[0], y = it[1])
                }
    }
}