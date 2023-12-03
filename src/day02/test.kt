package day02

import kotlin.test.Test
import utils.Day

class Day02 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(8)

  @Test fun part1Input() = assert(2541)

  // Part 2
  @Test fun part2Input() = assert(66016)
}
