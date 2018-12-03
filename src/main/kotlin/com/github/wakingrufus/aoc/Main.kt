package com.github.wakingrufus.aoc

fun main() {
    Day1().run {
        time { calculateFrequency(processInput()) }
    }.also { outputResult(1, 1, it) }
    Day1().run {
        time { part2(processInput()) }
    }.also { outputResult(1, 2, it) }
    Day2().run {
        val input = inputFileToLines("input-day2.txt")
        time { checksum(input) }.also { outputResult(2, 1, it) }
        time { part2(input) }.also { outputResult(2, 2, it) }
    }
    Day3().run {
        val input = inputFileToLines("input-day3.txt")
        time { findContestedArea(input) }
    }.also { outputResult(3, 1, it) }
}