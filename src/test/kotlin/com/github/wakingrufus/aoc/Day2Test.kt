package com.github.wakingrufus.aoc

import assertk.all
import assertk.assert
import assertk.assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2Test {

    @Test
    fun `test checksum example`() {
        assert(Day2()
                .checksum(listOf("abcdef","bababc","abbcde","abcccd","aabcdd","abcdee","ababab")))
                .isEqualTo(12)
    }

    @Test
    fun `test part2 example`(){
        val expected = "fgij".toCharArray()
        assert(Day2()
                .part2(listOf("abcde","fghij","klmno","pqrst","fguij","axcye","wvxyz")))
                .isEqualTo("fgij")
    }

    @Test
    fun `test stringsMatch`(){
        assert(Day2().stringsMatch("fghij" to "fguij")).isTrue()
        assert(Day2().stringsMatch("abcde" to "axcye")).isFalse()
    }

    @Test
    fun `test all combinations`(){
        assert(listOf("a","b","c","d").allCombinations().toList()).all {
            hasSize(6)
            containsExactly("a" to "b","a" to "c","a" to "d",
                    "b" to "c", "b" to "d",
                    "c" to "d")
        }
    }
}