package com.github.wakingrufus.aoc

import assertk.all
import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

internal class Day4Test {
    val sampleInput = listOf("[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up")

    @Test
    fun `test parseEvent start shift`() {
        val expected = ShiftStart(guardId = "10",
                timestamp = LocalDateTime.of(
                        LocalDate.of(1518, Month.NOVEMBER, 1),
                        LocalTime.MIDNIGHT))
        assert(name = "Start Shift Event",
                actual = parseEvent("[1518-11-01 00:00] Guard #10 begins shift"))
                .isEqualTo(expected)
    }

    @Test
    fun `test parseEvent fall asleep`() {
        val expected = SleepStart(LocalDateTime.of(
                LocalDate.of(1518, Month.NOVEMBER, 1),
                LocalTime.of(0, 5, 0)))
        assert(name = "Fall asleep Event",
                actual = parseEvent("[1518-11-01 00:05] falls asleep"))
                .isEqualTo(expected)
    }

    @Test
    fun `test parseEvent wake up`() {
        val expected = WakeUp(LocalDateTime.of(
                LocalDate.of(1518, Month.NOVEMBER, 1),
                LocalTime.of(0, 25, 0)))
        assert(name = "wake up Event",
                actual = parseEvent("[1518-11-01 00:25] wakes up"))
                .isEqualTo(expected)
    }

    @Test
    fun `test example`() {
        assert(Day4().part1(sampleInput)).isEqualTo(240)
    }

    @Test
    fun `test buildShifts example`() {
        val actual = Day4().run { buildShifts(parseAndOrderEvents(sampleInput)) }
        assert(actual.filter { it.guardId == "99" }).hasSize(3)
        assert(actual.filter { it.guardId == "10" }).hasSize(2)
        assert(actual).all {
            hasSize(5)
        }
    }

    @Test
    fun `test mostSleepy`() {
        assert(Day4().run { mostSleepyGuard(buildShifts(parseAndOrderEvents(sampleInput))) })
                .isEqualTo("10")
    }

    @Test
    fun `test mostCommonMinute`(){
        assert(Day4().run { mostCommonMinute(buildShifts(parseAndOrderEvents(sampleInput))
                .filter { it.guardId == "10" }) })
                .isEqualTo(24)
    }

    @Test
    fun `test Shift plus`() {
        assert(Shift(guardId = "1", sleepMap = mapOf(1 to 2, 2 to 3))
                + Shift(guardId = "1", sleepMap = mapOf(2 to 3, 3 to 4)))
                .isEqualTo(
                        Shift(guardId = "1",
                                sleepMap = mapOf(
                                        1 to 2,
                                        2 to 6,
                                        3 to 4))
                )
    }
}