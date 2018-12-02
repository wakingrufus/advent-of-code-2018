package com.github.wakingrufus.aoc

import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2Test {

    @Test
    fun `test checksum example`() {
        assertk.assert(Day2()
                .checksum(listOf("abcdef","bababc","abbcde","abcccd","aabcdd","abcdee","ababab")))
                .isEqualTo(12)
    }
}