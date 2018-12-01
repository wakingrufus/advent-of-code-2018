package com.github.wakingrufus.aoc

fun main(){
    Day1().run {
        calculateFrequency(processInput())
    }.also {
        System.out.println("Day 1 Part 1: $it")
    }
}