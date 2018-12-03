package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import mu.KLogging
import org.junit.jupiter.api.Test

internal class Day3Test {
    companion object : KLogging()

    @Test
    fun `test parseClaim`() {
        val expected = Claim(id = "123", xOffset = 3, yOffset = 2, width = 5, height = 4)
        assert(parseClaim("#123 @ 3,2: 5x4")).isEqualTo(expected)
    }

    @Test
    fun `test example`() {
        val exampleInput = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        assert(Day3().findContestedArea(exampleInput)).isEqualTo(4)
    }

    @Test
    fun `test expand`() {
        parseClaim("#3 @ 4,5: 2x1").expand().run {
            logger.info("expanded: " + this.toString())
            assert(size).isEqualTo(2)
            assert(this).containsExactly(4 to 5, 5 to 5)
        }
        assert(parseClaim("#3 @ 4,5: 2x1").expand())
                .containsExactly(4 to 5, 5 to 5)
        val input = Claim(id = "123", xOffset = 3, yOffset = 2, width = 5, height = 4)
        val actual = input.expand()
        assert(actual.size).isEqualTo(20)

    }

    @Test
    fun `test intersects`() {
        val exampleInput = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        val claims = exampleInput.map(::parseClaim)
        assert(claims[0].intersects(claims[1])).isTrue()
        assert(claims[0].intersects(claims[2])).isFalse()
        assert(claims[1].intersects(claims[2])).isFalse()

        assert(Claim("1", 1, 1, 2, 2)
                .intersects(Claim("2", 2, 2, 2, 2))).isTrue()
    }

    @Test
    fun `test example part 2`() {
        val exampleInput = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        assert(Day3().findNonIntersectingClaim(exampleInput)).isEqualTo("3")
    }
}