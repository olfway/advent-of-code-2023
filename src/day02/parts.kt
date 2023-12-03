package day02

import kotlin.math.max

//
// Common code
//
typealias GameSetRecord = String

data class Cubes(val color: String, val amount: Int)

typealias GameSet = List<Cubes>

fun getGameNumber(record: String): Int {
  return record.substringBefore(":").substringAfter(" ").toInt()
}

fun getGameSets(record: String): List<GameSetRecord> {
  return record.substringAfter(":").trim().split(";")
}

fun getCubes(gameSetRecord: GameSetRecord): GameSet {
  return gameSetRecord.split(",").map(String::trim).map {
    Cubes(it.substringAfter(" "), it.substringBefore(" ").toInt())
  }
}

//
// Part 1
//
fun part1(input: List<String>): Int {
  var result = 0
  val maxAvailable = mapOf("red" to 12, "green" to 13, "blue" to 14)
  record@ for (record in input) {
    for (gameSet in getGameSets(record)) {
      for (cubes in getCubes(gameSet)) {
        if (cubes.amount > maxAvailable.getValue(cubes.color)) {
          continue@record
        }
      }
    }
    result += getGameNumber(record)
  }
  return result
}

//
// Part 2
//
fun part2(input: List<String>): Int {
  var result = 0
  for (record in input) {
    val minimumSet = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
    for (gameSet in getGameSets(record)) {
      for (cubes in getCubes(gameSet)) {
        minimumSet[cubes.color] = max(minimumSet.getValue(cubes.color), cubes.amount)
      }
    }
    val power = minimumSet.values.reduce(Int::times)
    result += power
  }
  return result
}
