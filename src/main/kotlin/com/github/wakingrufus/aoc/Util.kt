package com.github.wakingrufus.aoc

fun inputFileToLines(fileName: String): List<String> {
    return ClassLoader.getSystemResourceAsStream(fileName)
            .bufferedReader()
            .use { it.readLines() }
}