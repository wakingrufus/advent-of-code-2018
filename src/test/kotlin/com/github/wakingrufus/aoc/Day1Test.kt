package com.github.wakingrufus.aoc

import assertk.all
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `test sample1`() {
        assertk.assert(Day1().calculateFrequency(listOf("+1", "-2", "+3", "+1")), "sample 1").all {
            isEqualTo(3)
        }
    }

    @Test
    fun `test processInput`() {
        Day1().processInput()
    }
}