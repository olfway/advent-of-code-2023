package day07

import kotlin.math.pow

//
// Common code
//
fun Int.pow(n: Int): Int {
  return this.toDouble().pow(n).toInt()
}

open class CalcStrength {
  open val cards = "23456789TJQKA"

  fun handCards(hand: String): Int {
    return hand
      .mapIndexed { index, card ->
        (cards.indexOf(card) + 1) * (cards.length.pow(hand.length - index))
      }
      .sum()
  }

  open fun hand(hand: String): Int {
    with(hand.groupingBy { it }.eachCount().values.sortedDescending()) {
      fun second() = get(1)
      return when (first()) {
        5 -> 6
        4 -> 5
        3 -> if (second() == 2) 4 else 3
        2 -> if (second() == 2) 2 else 1
        else -> 0
      }
    }
  }
}

class CalcStrengthWithJoker : CalcStrength() {
  override val cards = "J23456789TQKA"

  private fun getJokerReplacement(hand: String): Char {
    return hand
      .associateBy { char -> hand.count { it == char && char != 'J' } }
      .maxBy { it.key }
      .value
  }

  override fun hand(hand: String): Int {
    return super.hand(hand.replace('J', getJokerReplacement(hand)))
  }
}

fun getHandsAndBids(input: List<String>): Map<String, Int> {
  return input.associate { line -> with(line.split(" ")) { get(0) to get(1).toInt() } }
}

fun calc(input: List<String>, calcStrength: CalcStrength): Any {
  with(getHandsAndBids(input)) {
    return keys
      .sortedWith(compareBy({ calcStrength.hand(it) }, { calcStrength.handCards(it) }))
      .mapIndexed { index, hand -> getValue(hand) * (index + 1) }
      .sum()
  }
}

//
// Part 1
//
fun part1(input: List<String>): Any {
  return calc(input, CalcStrength())
}

//
// Part 2
//
fun part2(input: List<String>): Any {
  return calc(input, CalcStrengthWithJoker())
}
