package day05

import kotlin.test.Test
import utils.Day

class Day05 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(35)

  @Test fun part1Input() = assert(324724204)

  // Part 2
  @Test fun part2Test1() = assert(46)

  @Test fun part2Input() = assert(104070862)
}
