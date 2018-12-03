package com.github.wakingrufus.aoc

import mu.KLogging

class Day3 {
    companion object : KLogging()

    fun findContestedArea(claims: List<String>): Int {
        val claimedPlots: MutableSet<Pair<Int, Int>> = mutableSetOf()
        val contestedPlots: MutableSet<Pair<Int, Int>> = mutableSetOf()
        claims.map(::parseClaim)
                .flatMap(Claim::expand)
                .forEach {
                    if (!claimedPlots.add(it)) {
                        contestedPlots.add(it)
                    }
                }
        return contestedPlots.size
    }

    fun part2(claimInput: List<String>) = findNonIntersectingClaim(claimInput)

    fun findNonIntersectingClaim(claimInput: List<String>): String? {
        val claimList = claimInput.map { parseClaim(it) }
        return claimList.find { subject ->
            claimList.none {
                subject.id != it.id && subject.intersects(it)
            }
        }?.id
    }
}

data class Claim(val id: String,
                 val xOffset: Int,
                 val yOffset: Int,
                 val width: Int,
                 val height: Int)

fun Claim.expand(): List<Pair<Int, Int>> {
    return (xOffset until xOffset + width).flatMap { x ->
        (yOffset until yOffset + height).map { y ->
            x to y
        }
    }
}

fun Claim.intersects(otherClaim: Claim): Boolean {
    return this.xOffset < (otherClaim.xOffset + otherClaim.width) &&
            (this.xOffset + this.width) > otherClaim.xOffset &&
            this.yOffset < (otherClaim.yOffset + otherClaim.height) &&
            (this.yOffset + this.height) > otherClaim.yOffset
}

fun parseClaim(input: String): Claim {
    val tokens = input
            .split('#', ':', ',', 'x', ':', '@')
            .map(String::trim)
    return Claim(id = tokens[1],
            xOffset = tokens[2].toInt(),
            yOffset = tokens[3].toInt(),
            width = tokens[4].toInt(),
            height = tokens[5].toInt())
}