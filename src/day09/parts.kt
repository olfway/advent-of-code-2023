package day09

//
// Common code
//
fun parse(input: List<String>): List<List<Int>> {
  return input.map { it.split(' ').map(String::toInt) }
}

fun differences(values: List<Int>): List<List<Int>> {
  return generateSequence(values) { list -> list.windowed(2).map { it.last() - it.first() } }
    .takeWhile { list -> !list.all { it == 0 } }
    .toList()
}

//
// Part 1
//
fun part1(input: List<String>): Any {
  return parse(input).sumOf { differences(it).foldRight(0L) { values, acc -> values.last() + acc } }
}

//
// Part 2
//
fun part2(input: List<String>): Any {
  return parse(input).sumOf {
    differences(it).foldRight(0L) { values, acc -> values.first() - acc }
  }
}
