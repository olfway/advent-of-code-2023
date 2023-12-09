package day08

import kotlin.test.Test
import utils.Day

class Day08 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(2)

  @Test fun part1Test2() = assert(6)

  @Test fun part1Input() = assert(16697)

  // Part 2
  @Test fun part2Test3() = assert(6)

  @Test fun part2Input() = assert(10668805667831)
}
