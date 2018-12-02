package com.github.wakingrufus.aoc

import mu.KLogging

val letters = ('a'..'z')

class Day2 {
    companion object : KLogging()

    fun processInput(): List<String> = inputFileToLines("input-day2.txt")

    fun checksum(boxIds: List<String>): Int {
        val twos = boxIds.count { id ->
            letters.any { letter ->
                id.containsNOf(times = 2, letter = letter)
            }
        }
        val threes = boxIds.count { id ->
            letters.any { letter ->
                id.containsNOf(times = 3, letter = letter)
            }
        }
        return twos * threes
    }

    fun part2(boxIds: List<String>): String {
        return boxIds.allCombinations()
                .first(this@Day2::stringsMatch)
                .let {
                    logger.info("found match: " + it.first + " and " + it.second)
                    it.first.toCharArray()
                            .filterIndexed { i, c -> it.second.toCharArray()[i] == c }
                            .toCharArray()
                }.let {
                    String(it)
                }
    }

    fun stringsMatch(strings: Pair<String, String>): Boolean {
        return strings.first.length == strings.second.length
                && strings.first.toCharArray()
                .zip(strings.second.toCharArray())
                .count { it.first != it.second } == 1
    }
}

fun String.containsNOf(times: Int, letter: Char): Boolean = this.count { it == letter } == times

fun <T> List<T>.allCombinations(): Sequence<Pair<T, T>> {
    val collection = this
    return sequence {
        for (i in 0 until collection.size) {
            for (s in i + 1 until collection.size) {
                yield(collection[i] to collection[s])
            }
        }
    }
}