package com.github.wakingrufus.aoc

fun main(){
    Day1().run {
        calculateFrequency(processInput())
    }.also {
        System.out.println("Day 1 Part 1: $it")
    }
    Day1().run {
        part2(processInput())
    }.also {
        System.out.println("Day 1 Part 2: $it")
    }
}