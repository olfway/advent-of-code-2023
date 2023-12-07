package day06

import kotlin.test.Test
import utils.Day

class Day06 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(288)

  @Test fun part1Input() = assert(608902)

  // Part 2
  @Test fun part2Test1() = assert(71503)

  @Test fun part2Input() = assert(46173809)
}
