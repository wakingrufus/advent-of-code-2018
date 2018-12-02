package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test


class Day1Test {

    @Test
    fun `test sample1`() {
        testPart1(name = "sample 1", input = listOf("+1", "-2", "+3", "+1"), expected = 3)
    }

    @Test
    fun `test sample2`() {
        testPart1(name = "sample 2", input = listOf("+1", "+1", "+1"), expected = 3)
    }

    @Test
    fun `test sample3`() {
        testPart1(name = "sample 3", input = listOf("+1", "+1", "-2"), expected = 0)
    }

    @Test
    fun `test sample4`() {
        testPart1(name = "sample 4", input = listOf("-1", "-2", "-3"), expected = -6)
    }

    @Test
    fun `test processInput`() {
        Day1().processInput()
    }

    private fun testPart1(name: String, input: List<String>, expected: Int) {
        assert(Day1().calculateFrequency(input), name).isEqualTo(expected)
    }

    @Test
    fun `test part2 sample 1`(){
        assert(Day1().part2(listOf("+1","-1"))).isEqualTo(0)
    }

    @Test
    fun `test part2 sample 2`(){
        assert(Day1().part2(listOf("+3", "+3", "+4", "-2", "-4"))).isEqualTo(10)
    }

    @Test
    fun `test part2 sample 3`(){
        assert(Day1().part2(listOf("-6", "+3", "+8", "+5", "-6"))).isEqualTo(5)
    }

    @Test
    fun `test part2 sample 4`(){
        assert(Day1().part2(listOf("+7", "+7", "-2", "-7", "-4"))).isEqualTo(14)
    }
}