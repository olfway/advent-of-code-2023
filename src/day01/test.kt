package day01

import kotlin.test.Test
import utils.Day

class Day01 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(142)

  @Test fun part1Input() = assert(57346)

  // Part 2
  @Test fun part2Test2() = assert(281)

  @Test fun part2Test3() = assert(162)

  @Test fun part2Input() = assert(57345)
}
