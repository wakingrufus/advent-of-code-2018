package com.github.wakingrufus.aoc

import mu.KLogging


class Day1 {
    companion object : KLogging()

    /**
     * Part 1 solution
     */
    fun calculateFrequency(values: List<String>): Int {
        return values.map(String::toInt).sum()
    }

    fun processInput(): List<String> {
        return ClassLoader.getSystemResourceAsStream("input-day1.txt")
                .bufferedReader()
                .use { it.readLines() }
                .map {
                    logger.info(it)
                    it
                }
    }

    fun part2(values: List<String>): Int {
        val intValues = values.map(String::toInt)
        var freq = 0
        val seenBefore = mutableSetOf(freq)
        return sequence {
            while (true) {
                yieldAll(intValues)
            }
        }.map {
            freq += it
            freq
        }.first {
            !seenBefore.add(it)
        }
    }
}
