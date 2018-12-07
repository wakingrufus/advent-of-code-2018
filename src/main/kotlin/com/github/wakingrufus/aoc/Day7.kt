package com.github.wakingrufus.aoc

import mu.KLogging

class Day7 {
    companion object : KLogging()

    fun part1(input: List<String>): String {
        return findOrder(input.map { parseInput(it) })
    }

    fun findOrder(input: List<Pair<Char, Char>>): String {
        val dependsOnMap: MutableMap<Char, Set<Char>> = mutableMapOf()
        val dependencyOfMap: MutableMap<Char, Set<Char>> = mutableMapOf()
        val executedStepList: MutableList<Char> = mutableListOf()
        val executedSteps: MutableSet<Char> = mutableSetOf()
        input.forEach {
            dependsOnMap[it.second] = dependsOnMap.getOrDefault(it.second, setOf()).plus(it.first)
            dependencyOfMap[it.first] = dependencyOfMap.getOrDefault(it.first, setOf()).plus(it.second)
        }
        logger.info("dependsOn: " + dependsOnMap)
        logger.info("dependencyOf: " + dependencyOfMap)
        var candidates = dependencyOfMap.keys - dependsOnMap.keys
        logger.info("Initial tasks:" + candidates.joinToString(""))
        while (candidates.isNotEmpty()) {
            val stepToRun = candidates.filter {
                executedSteps.containsAll(dependsOnMap.getOrDefault(it, setOf()))
            }.sorted().first()
            executedSteps.add(stepToRun)
            executedStepList.add(stepToRun)
            logger.info { "run steps: " + stepToRun }
            candidates = dependencyOfMap.getOrDefault(stepToRun, setOf())
                    .plus(candidates)
                    .minus(executedSteps)

        }
        return executedStepList.joinToString("")
    }

    fun parseInput(input: String): Pair<Char, Char> {
        return input[5] to input[36]
    }
}