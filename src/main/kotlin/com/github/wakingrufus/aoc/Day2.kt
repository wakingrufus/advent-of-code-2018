package com.github.wakingrufus.aoc

class Day2 {
    companion object {
        val letters = ('a'..'z')
    }

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


}

fun String.containsNOf(times: Int, letter: Char): Boolean = this.count { it == letter } == times