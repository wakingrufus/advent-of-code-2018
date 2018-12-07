package com.github.wakingrufus.aoc

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7Test {

    @Test
    fun `test parseInput`() {
        assert(Day7().parseInput("Step C must be finished before step A can begin."))
                .isEqualTo('C' to 'A')
    }

    @Test
    fun `test example part 1`(){
        val exampleInput = listOf(
                "Step C must be finished before step A can begin." ,
                "Step C must be finished before step F can begin." ,
                "Step A must be finished before step B can begin." ,
                "Step A must be finished before step D can begin." ,
                "Step B must be finished before step E can begin." ,
                "Step D must be finished before step E can begin." ,
                "Step F must be finished before step E can begin.")
        assert(Day7().part1(exampleInput)).isEqualTo("CABDFE")
    }
}