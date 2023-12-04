package day04

import kotlin.math.pow

//
// Common code
//
fun parseCards(input: List<String>): Map<Int, Int> {
  // Parse lines like this:
  // Card   1: 72  2 34  7 | 63  3 64 33
  // Card 205: 54 17  3 26 |  5 29 61 49
  val cards = mutableMapOf<Int, Int>()
  for (line in input) {
    val cardId = line.substringAfter(" ").substringBefore(":").trim().toInt()
    val wins = line.substringAfter(":").substringBefore("|").split(" ").map { it.toIntOrNull() }
    val numbers = line.substringAfter("|").split(" ").mapNotNull { it.toIntOrNull() }

    cards[cardId] = (numbers.toSet() intersect wins.toSet()).size
  }
  return cards
}

//
// Part 1
//
fun part1(input: List<String>): Int {
  return parseCards(input).values.sumOf { 2.toDouble().pow(it - 1).toInt() }
}

//
// Part 2
//
fun part2(input: List<String>): Int {
  val cards = parseCards(input)
  val copies = cards.mapValues { 1 }.toMutableMap()
  for (card in cards) {
    for (copyCard in (card.key + 1) ..< (card.key + 1 + card.value)) {
      copies[copyCard] = copies[copyCard]!! + copies[card.key]!!
    }
  }
  return copies.values.sum()
}
