package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test


class Day1Test {

    @Test
    fun `test sample1`() {
        test(name = "sample 1", input = listOf("+1", "-2", "+3", "+1"), expected = 3)
    }

    @Test
    fun `test sample2`() {
        test(name = "sample 2", input = listOf("+1", "+1", "+1"), expected = 3)
    }

    @Test
    fun `test sample3`() {
        test(name = "sample 3", input = listOf("+1", "+1", "-2"), expected = 0)
    }

    @Test
    fun `test sample4`() {
        test(name = "sample 4", input = listOf("-1", "-2", "-3"), expected = -6)
    }

    @Test
    fun `test processInput`() {
        Day1().processInput()
    }

    private fun test(name: String, input: List<String>, expected: Int) {
        assert(Day1().calculateFrequency(input), name).isEqualTo(expected)
    }
}