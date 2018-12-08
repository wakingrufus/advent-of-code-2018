package com.github.wakingrufus.aoc

import mu.KLogging
import java.util.*

class Day8 {
    companion object : KLogging()

    fun part1(input: String): Int {
        return buildNodeList(parseInput(input)).flatMap { it.metadata }.sum()
    }

    fun parseInput(input: String): List<Int> {
        return input.split(" ")
                .map { it.toInt() }
    }

    fun buildNodeList(data: List<Int>): List<Node> {
        val stack = Stack<Node>()
        var i = 0
        val completedNodes: MutableList<Node> = mutableListOf()
        while (i < data.size) {
            if (stack.isEmpty()) {
                stack.push(Node(data[i], data[i + 1]))
                i += 2
            } else {
                if (stack.peek().hasAllNodes()) {
                    if (stack.peek().hasAllMetadata()) {
                        val completedNode = stack.pop()
                        completedNodes.add(completedNode)
                        if (stack.isNotEmpty()) {
                            stack.peek().children.add(completedNode)
                        }
                    } else {
                        stack.peek().metadata.add(data[i])
                        i++
                    }
                } else {
                    stack.push(Node(data[i], data[i + 1]))
                    i += 2
                }
            }
        }
        if (stack.isNotEmpty()) {
            val completedNode = stack.pop()
            completedNodes.add(completedNode)
        }
        return completedNodes.toList()
    }

    fun part2(input: String): Int {
        return buildNodeList(parseInput(input)).last().value()
    }

}

data class Node(val totalChildren: Int,
                val totalMetadata: Int,
                val children: MutableList<Node> = mutableListOf(),
                val metadata: MutableList<Int> = mutableListOf()) {
    fun hasAllNodes() = children.size == totalChildren
    fun hasAllMetadata() = metadata.size == totalMetadata
    fun value() : Int {
        return if (totalChildren > 0) {
            metadata.map {
                children.getOrNull(it-1)
            }.map { it?.value() ?: 0 }.sum()
        } else {
            metadata.sum()
        }
    }
}