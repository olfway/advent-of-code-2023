package day09

import kotlin.test.Test
import utils.Day

class Day09 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(114)

  @Test fun part1Input() = assert(1882395907)

  // Part 2
  @Test fun part2Test1() = assert(2)

  @Test fun part2Input() = assert(1005)
}
