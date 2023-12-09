package day08

//
// Common code
//
fun directions(line: String): () -> Sequence<Char> {
  return { generateSequence { line.asIterable() }.flatten() }
}

fun routes(lines: List<String>): Map<String, Map<Char, String>> {
  fun String.removeAll(remove: String): String {
    return this.filterNot { it in remove }
  }

  return lines.associate {
    with(it.removeAll("=(,)").split(' ')) { get(0) to mapOf('L' to get(2), 'R' to get(3)) }
  }
}

//
// Part 1
//
fun part1(input: List<String>): Any {
  val routes = routes(input.drop(2))
  val directions = directions(input.first())
  directions().foldIndexed("AAA") { index, node, direction ->
    routes.getValue(node).getValue(direction).let { if (it != "ZZZ") it else return index + 1 }
  }
  return -1
}

//
// Part 2
//
fun part2(input: List<String>): Any {

  fun gcd(a: Long, b: Long): Long {
    if (a == 0L) return b
    return gcd(b % a, a)
  }

  fun lcm(numbers: List<Long>, index: Int = 0): Long {
    if (index == numbers.lastIndex) return numbers.last()
    val a = numbers[index]
    val b = lcm(numbers, index + 1)
    return a * b / gcd(a, b)
  }

  val routes = routes(input.drop(2))
  val directions = directions(input.first())

  fun calcStepsFrom(startNode: String): Long {
    directions().foldIndexed(startNode) { index, node, direction ->
      routes.getValue(node).getValue(direction).let {
        if (!it.endsWith('Z')) it else return index + 1L
      }
    }
    throw Exception("Not accessible")
  }

  val steps = routes.keys.filter { it.endsWith('A') }.map { node -> calcStepsFrom(node) }

  return lcm(steps)
}
