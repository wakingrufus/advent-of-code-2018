package com.github.wakingrufus.aoc

class Day5 {

    fun part1(input: String): Int = reduce(input).length
    fun part2(input: String): Int = 'a'.rangeTo('z')
            .map { reduceWithout(input, it) }
            .map { it.length }
            .min() ?: -1

    fun reduceWithout(string: String, char: Char): String =
            reduce(string.replace(char.toString(), "", true))

    fun reduce(string: String): String {
        val bufferedString = Char.MIN_LOW_SURROGATE + string + Char.MAX_HIGH_SURROGATE
        val zippedData = bufferedString.toCharArray().toList().zipWithNext().zipWithNext()
        val reducedString = zippedData.flatMap {
            if (opposites(it.first) xor opposites(it.second)) {
                listOf()
            } else if (opposites(it.first) && opposites(it.second)) {
                listOf(it.second.second)
            } else {
                listOf(it.first.second)
            }
        }.joinToString("")
        if (reducedString.length == string.length) {
            return reducedString
        }
        return reduce(reducedString)
    }

    fun opposites(pair: Pair<Char, Char>): Boolean =
            pair.first != pair.second
                    && pair.first.toString().toUpperCase() == pair.second.toString().toUpperCase()

}