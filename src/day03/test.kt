package day03

import kotlin.test.Test
import utils.Day

class Day03 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(4361)

  @Test fun part1Input() = assert(535235)

  // Part 2
  @Test fun part2Test1() = assert(467835)

  @Test fun part2Test2() = assert(848524)

  @Test fun part2Input() = assert(79844424)
}
