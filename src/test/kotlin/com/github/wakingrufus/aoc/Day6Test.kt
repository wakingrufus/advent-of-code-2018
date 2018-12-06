package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isEqualToWithGivenProperties
import org.junit.jupiter.api.Test

internal class Day6Test {
    @Test
    fun `test parse`() {
        assert(Day6().parseCoordinate("1, 1"))
                .isEqualToWithGivenProperties(Coordinate(1, 1), Coordinate::x, Coordinate::y)
    }

    @Test
    fun `test part 1 example`() {
        val exampleInput = listOf("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9")
        assert(Day6().part1(exampleInput)).isEqualTo(17)
    }
}