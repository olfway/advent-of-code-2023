package day04

import kotlin.test.Test
import utils.Day

class Day04 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(13)

  @Test fun part1Input() = assert(24542)

  // Part 2
  @Test fun part2Test1() = assert(30)

  @Test fun part2Input() = assert(8736438)
}
