package day06

//
// Part 1
//
fun part1(input: List<String>): Any {
  val (times, distances) = input.map { line -> line.split(" ").mapNotNull { it.toIntOrNull() } }
  val results = times.zip(distances).toMap()
  return results
    .map { (time, distance) -> (1..time).count { it * (time - it) > distance } }
    .reduce(Int::times)
}

//
// Part 2
//
fun part2(input: List<String>): Any {
  val (time, distance) = input.map { line -> line.substringAfter(":").replace(" ", "").toLong() }
  val (minTimeToWin, maxTimeToWin) =
    listOf((1..time), (time downTo 1)).map { range ->
      range.asSequence().filter { it * (time - it) > distance }.first()
    }
  return maxTimeToWin - minTimeToWin + 1
}
