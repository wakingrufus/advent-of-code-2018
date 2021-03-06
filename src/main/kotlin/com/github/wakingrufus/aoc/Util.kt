package com.github.wakingrufus.aoc

import java.time.Duration
import java.time.Instant

fun inputFileToLines(fileName: String): List<String> {
    return ClassLoader.getSystemResourceAsStream(fileName)
            .bufferedReader()
            .use { it.readLines() }
}

class TimedResult<T>(val duration: Duration, val result: T)

fun <T> time(function: ()->T): TimedResult<T>{
    val startTime = Instant.now()
    val result = function()
    val endTime =  Instant.now()
    return TimedResult(duration = Duration.between(startTime, endTime), result = result)
}

fun <T> outputResult(day: Int, part: Int,timedResult: TimedResult<T>){
    timedResult.also {
        System.out.println("Day $day Part $part Time: ${it.duration.toMillis()} ms \tResult: ${it.result}")
    }
}