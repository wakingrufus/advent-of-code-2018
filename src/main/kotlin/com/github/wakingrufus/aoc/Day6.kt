package com.github.wakingrufus.aoc

import kotlin.math.absoluteValue

data class Coordinate(val x: Int, val y: Int) {
    fun manhattanDistance(other: Coordinate): Int =
            (this.x - other.x).absoluteValue + (this.y - other.y).absoluteValue
}

class Day6 {
    fun part1(input: List<String>): Int {
        return findLargestArea(input.map(this::parseCoordinate))
    }

    fun part2(input: List<String>): Int {
        return safeZoneSize(input.map(this::parseCoordinate), 10000)
    }

    fun allPoints(coordinates: List<Coordinate>): List<Pair<Int, Int>> {
        val xMax = coordinates.maxBy { it.x }?.x ?: 1
        val xMin = coordinates.minBy { it.x }?.x ?: 0
        val yMax = coordinates.maxBy { it.y }?.y ?: 1
        val yMin = coordinates.minBy { it.y }?.y ?: 0
        return xMin.rangeTo(xMax)
                .flatMap { x ->
                    yMin.rangeTo(yMax)
                            .map { y ->
                                x to y
                            }
                }
    }

    fun safeZoneSize(coordinates: List<Coordinate>, distance: Int): Int {
        return allPoints(coordinates)
                .filter { point ->
                    coordinates.sumBy {
                        it.manhattanDistance(Coordinate(point.first, point.second))
                    } < distance
                }
                .count()
    }

    fun findLargestArea(coordinates: List<Coordinate>): Int {
        return allPoints(coordinates)
                .map { point -> findClosestCoordinate(coordinates, point.first, point.second) }
                .groupBy { it }
                .maxBy { it.value.size }?.value?.size ?: 0
    }


    fun findClosestCoordinate(coordinates: List<Coordinate>, x: Int, y: Int): Coordinate? {
        val thisCoordinate = Coordinate(x, y)
        val allDistances = coordinates.map {
            it to it.manhattanDistance(thisCoordinate)
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
