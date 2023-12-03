package day01

//
// Part 1
//
fun part1(input: List<String>): Any {
  return input.sumOf { chars ->
    chars.filter(Char::isDigit).let { digits -> "${digits.first()}${digits.last()}".toInt() }
  }
}

//
// Part 2
//
fun part2(input: List<String>): Any {

  val digitsMap =
    listOf(
        (0..9).map { it.toString() to it },
        "one,two,three,four,five,six,seven,eight,nine".split(",").mapIndexed { i, n -> n to i + 1 }
      )
      .flatten()
      .toMap()

  val digitsMatcher =
    Regex(digitsMap.keys.joinToString(prefix = "(?=(", postfix = "))", separator = "|"))

  return input.sumOf { inputLine ->
    digitsMatcher
      .findAll(inputLine)
      .map { result -> digitsMap.getValue(result.groupValues[1]) }
      .let { digits -> digits.first() * 10 + digits.last() }
  }
}
