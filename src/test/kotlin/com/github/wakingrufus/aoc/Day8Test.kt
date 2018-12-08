package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day8Test {
    @Test
    fun `test example part 1`() {
        val exampleInput = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"
        assert(Day8().part1(exampleInput)).isEqualTo(138)
    }
}