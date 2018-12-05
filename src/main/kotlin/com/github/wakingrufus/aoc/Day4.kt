package com.github.wakingrufus.aoc

import mu.KLogging
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

sealed class Event(val time: LocalDateTime)

data class ShiftStart(val guardId: String, val timestamp: LocalDateTime) : Event(timestamp)

data class SleepStart(val timestamp: LocalDateTime) : Event(timestamp)

data class WakeUp(val timestamp: LocalDateTime) : Event(timestamp)

data class InvalidEvent(val input: String) : Event(LocalDateTime.MIN)

data class Shift(
        val guardId: String,
        val sleepMap: Map<Int, Int>) {
    operator fun plus(otherShift: Shift): Shift {
        val newMap = this.sleepMap.toMutableMap()
        otherShift.sleepMap.entries.forEach {
            newMap[it.key] = it.value + this.sleepMap.getOrDefault(it.key, 0)
        }
        return Shift(guardId = guardId, sleepMap = newMap)
    }
}

data class EventAccumulator(
        val currentGuard: String? = null,
        val sleepMap: Map<Int, Int> = mapOf(),
        val shifts: List<Shift> = listOf()
)

class Day4 {
    companion object : KLogging()

    fun part1(input: List<String>): Int {
        val validEvents = parseAndOrderEvents(input)
        val shifts = buildShifts(validEvents)
        val mostSleepyGuard = mostSleepyGuard(shifts)
        val mostCommonMinute = mostCommonMinute(shifts.filter { it.guardId == mostSleepyGuard })
        val sleepyGuardNumber = mostSleepyGuard?.toIntOrNull() ?: 0
        return mostCommonMinute * sleepyGuardNumber
    }

    fun part2(input: List<String>): Int {
        val validEvents = parseAndOrderEvents(input)
        val shifts = buildShifts(validEvents)
        return mostCommonGuardAndMinute(shifts).let {
            it.first.toInt() * it.second
        }
    }

    fun mostCommonGuardAndMinute(shifts: List<Shift>): Pair<String, Int> {
        val shiftsByGuard = shifts.groupBy { it.guardId }
        val combinedShiftsByGuard = shiftsByGuard
                .mapValues {
                    it.value.reduce(operation = { a, b -> a + b })
                }
        val maxMinuteAndTimesByGuard = combinedShiftsByGuard
                .mapValues {
                    it.value.sleepMap
                            .maxBy { it.value }
                            ?.let {
                                it.key to it.value
                            } ?: -1 to -1
                }
        return maxMinuteAndTimesByGuard.maxBy { it.value.second }?.let {
            it.key to it.value.first
        } ?: "-1" to -1
    }

    fun parseAndOrderEvents(input: List<String>): List<Event> {
        return input.map { parseEvent(it) }
                .filterNot { event ->
                    (event is InvalidEvent)
                            .also { if (event is InvalidEvent) logger.warn("invalid data: ${event.input}") }
                }
                .sortedBy { it.time }
    }

    fun buildShifts(events: List<Event>): List<Shift> {
        return events
                .plus(events[0])
                .zipWithNext()
                .fold(EventAccumulator()) { acc, eventPair ->
                    val firstEvent = eventPair.first
                    val secondEvent = eventPair.second
                    var newAcc = acc
                    if (firstEvent is SleepStart && secondEvent is WakeUp || secondEvent is ShiftStart) {
                        newAcc = eventPair.first.time.minute.until(eventPair.second.time.minute)
                                .fold(acc) { a, minute ->
                                    a.copy(sleepMap = a.sleepMap + (minute to (a.sleepMap[minute] ?: 0) + 1))
                                }
                    }
                    if (secondEvent is ShiftStart) {
                        newAcc = (acc.currentGuard?.let {
                            acc.copy(
                                    shifts = acc.shifts + Shift(guardId = it, sleepMap = acc.sleepMap),
                                    sleepMap = mapOf()
                            )
                        } ?: acc).copy(currentGuard = secondEvent.guardId)
                    }
                    if (firstEvent is ShiftStart && acc.currentGuard != firstEvent.guardId) {
                        newAcc = acc.copy(
                                currentGuard = firstEvent.guardId
                        )
                    }
                    newAcc
                }.shifts
    }

    fun mostSleepyGuard(shifts: List<Shift>): String? = shifts
            .groupBy({ s -> s.guardId }) {
                it.sleepMap.values.sum()
            }.maxBy { it.value.sum() }?.key

    fun mostCommonMinute(shifts: List<Shift>): Int {
        return shifts.reduce(operation = { a, b ->
            a + b
        }).sleepMap
                .maxBy { it.value }?.key ?: 0
    }

}

val localDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

fun parseEvent(input: String): Event {
    val tokens = input.split('[', ']')
    val timestamp = LocalDateTime.parse(tokens[1], localDateFormat)
    val actionText = tokens[2]
    return when {
        actionText.contains("Guard") -> {
            val guardId = actionText
                    .substring(actionText.indexOf('#') + 1)
                    .substringBefore(' ')
            ShiftStart(guardId = guardId, timestamp = timestamp)
        }
        actionText.contains("falls asleep") -> SleepStart(timestamp = timestamp)
        actionText.contains("wakes up") -> WakeUp(timestamp)
        else -> InvalidEvent(input)
    }
}