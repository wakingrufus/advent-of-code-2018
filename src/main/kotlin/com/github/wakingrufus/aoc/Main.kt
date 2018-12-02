package com.github.wakingrufus.aoc

fun main() {
    Day1().run {
        time { calculateFrequency(processInput()) }
    }.also { outputResult(1, 1, it) }
    Day1().run {
        time { part2(processInput()) }
    }.also { outputResult(1, 2, it) }
    Day2().run {
        time { checksum(processInput()) }
    }.also { outputResult(2, 1, it) }
    Day2().run {
        time { part2(processInput()) }
    }.also { outputResult(2, 2, it) }
}