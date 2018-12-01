package com.github.wakingrufus.aoc

import mu.KLogging


class Day1 {
    companion object : KLogging()

    fun calculateFrequency(values: List<String>): Int {
        return values.map(String::toInt).sum()
    }

    fun processInput(): List<String> {
        return ClassLoader.getSystemResourceAsStream("input-day1.txt").bufferedReader().use { it.readLines() }.map {
            logger.info(it)
            it
        }
    }
}

