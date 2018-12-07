package com.github.wakingrufus.aoc

import mu.KLogging

class Day7 {
    companion object : KLogging()

    fun part1(input: List<String>): String {
        return findOrder(input.map(this::parseInput))
    }

    fun part2(input: List<String>): Int {
        return findTime(input.map(this::parseInput))
    }

    fun findTime(input: List<Pair<Char, Char>>, workers: Int = 5, baseTaskTime: Int = 60): Int {
        val tracker = TaskTracker(workers = workers, baseTaskTime = baseTaskTime)
        input.forEach(tracker::addTaskDependency)
        while (tracker.hasMoreWork() || tracker.hasInProgressWork()) {
            tracker.tick()
        }
        return tracker.getEndTime() - 1
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
        var candidates = dependencyOfMap.keys - dependsOnMap.keys
        while (candidates.isNotEmpty()) {
            val stepToRun = candidates.filter {
                executedSteps.containsAll(dependsOnMap.getOrDefault(it, setOf()))
            }.sorted().first()
            executedSteps.add(stepToRun)
            executedStepList.add(stepToRun)
            logger.info { "run step: " + stepToRun }
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


class TaskTracker(val workers: Int, val baseTaskTime: Int = 60) {
    companion object : KLogging()

    private val letterList = ('A'..'Z').toList()
    private val dependsOnMap: MutableMap<Char, Set<Char>> = mutableMapOf()
    private val dependencyOfMap: MutableMap<Char, Set<Char>> = mutableMapOf()
    private val executedStepList: MutableList<Char> = mutableListOf()
    private val executedSteps: MutableSet<Char> = mutableSetOf()
    val candidates: MutableSet<Char> by lazy { (dependencyOfMap.keys - dependsOnMap.keys).toMutableSet() }
    private val inProgress: MutableList<Pair<Char, Int>> = mutableListOf()
    private var currentTime: Int = 0

    fun addTaskDependency(pair: Pair<Char, Char>) {
        dependsOnMap[pair.second] = dependsOnMap.getOrDefault(pair.second, setOf()).plus(pair.first)
        dependencyOfMap[pair.first] = dependencyOfMap.getOrDefault(pair.first, setOf()).plus(pair.second)
    }

    fun getEndTime(): Int {
        return inProgress.map { it.second }.max() ?: currentTime
    }

    fun hasMoreWork(): Boolean {
        return candidates.isNotEmpty()
    }

    fun taskTime(task: Char): Int {
        return letterList.indexOf(task) + 1
    }

    fun hasInProgressWork(): Boolean {
        return inProgress.isNotEmpty()
    }

    fun nextTask(): Char {
        return candidates.sorted().first()
    }

    fun tick() {
        inProgress.filter { it.second <= currentTime }.forEach {
            inProgress.remove(it)
            complete(it.first)
            logger.info("task ${it.first} completed at t=$currentTime")
        }
        while (hasMoreWork() && inProgress.size < workers) {
            start(nextTask())
        }
        currentTime++
    }

    fun start(step: Char) {
        inProgress.add(step to currentTime + baseTaskTime + taskTime(step))
        candidates.remove(step)
        logger.info("step ${step} started at t=$currentTime")
    }

    fun complete(step: Char) {
        executedSteps.add(step)
        executedStepList.add(step)
        candidates.addAll(dependencyOfMap.getOrDefault(step, setOf()).filter {
            executedSteps.containsAll(dependsOnMap.getOrDefault(it, setOf()))
        })
        candidates.removeAll(executedSteps)
        candidates.removeAll(inProgress.map(Pair<Char, Int>::first))
    }
}