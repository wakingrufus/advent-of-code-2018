package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day5Test{
    @Test
    fun `test reduce`(){
        Day5().run {
            assert(reduce("aA")).isEqualTo("")
            assert(reduce("abBA")).isEqualTo("")
            assert(reduce("abAB")).isEqualTo("abAB")
            assert(reduce("aabAAB")).isEqualTo("aabAAB")
            assert(reduce("dabAcCaCBAcCcaDA")).isEqualTo("dabCBAcaDA")
        }
    }

    @Test
    fun `test part1 example`(){
        Day5().run {
            assert(part1("dabAcCaCBAcCcaDA")).isEqualTo(10)
        }
    }
}