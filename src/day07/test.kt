package day07

import kotlin.test.Test
import utils.Day

class Day07 : Day() {
  override val parts = listOf(::part1, ::part2)

  // Part 1
  @Test fun part1Test1() = assert(6440)

  @Test fun part1Input() = assert(253954294)

  // Part 2
  @Test fun part2Test1() = assert(5905)

  @Test fun part2Input() = assert(254837398)
}
