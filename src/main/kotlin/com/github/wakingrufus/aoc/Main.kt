package com.github.wakingrufus.aoc

fun main() {
    Day1().run {
        val input = processInput()
        time { calculateFrequency(input) }.also { outputResult(1, 1, it) }
        time { part2(input) }.also { outputResult(1, 2, it) }
    }

    Day2().run {
        val input = inputFileToLines("input-day2.txt")
        time { checksum(input) }.also { outputResult(2, 1, it) }
        time { part2(input) }.also { outputResult(2, 2, it) }
    }

    Day3().run {
        val input = inputFileToLines("input-day3.txt")
        time { findContestedArea(input) }.also { outputResult(3, 1, it) }
        time { part2(input) }.also { outputResult(3, 2, it) }
    }

    Day4().run {
        val input = inputFileToLines("input-day4.txt")
        time { part1(input) }.also { outputResult(4, 1, it) }
        time { part2(input) }.also { outputResult(4, 2, it) }
    }

    Day5().run {
        val input = inputFileToLines("input-day5.txt")
//        time { part1(input[0]) }.also { outputResult(5, 1, it) }
//        time { part2(input[0]) }.also { outputResult(5, 2, it) }
        time { part1Fast(input[0]) }.also { outputResult(5, 1, it) }
        time { part2Fast(input[0]) }.also { outputResult(5, 2, it) }
    }

    Day6().run {
        val input = inputFileToLines("input-day6.txt")
        time { part1(input) }.also { outputResult(6, 1, it) }
        time { part2(input) }.also { outputResult(6, 2, it) }
    }

    Day7().run {
        val input = inputFileToLines("input-day7.txt")
        time { part1(input) }.also { outputResult(7, 1, it) }
    }
}