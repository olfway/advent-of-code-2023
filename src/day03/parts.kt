package day03

//
// Common code
//
fun <T> makeTable(rows: Int, cols: Int, value: () -> T): List<MutableList<T>> =
  List(rows) { MutableList(cols) { value() } }

fun findAdjacentCells(table: List<List<*>>, row: Int, col: Int): List<Pair<Int, Int>> {
  val rowRange = (if (row > 0) -1 else 0)..(if (row < table.size - 1) 1 else 0)
  val colRange = (if (col > 0) -1 else 0)..(if (col < table.first().size - 1) 1 else 0)
  return rowRange.flatMap { i -> colRange.map { j -> Pair(row + i, col + j) } }
}

fun findAllInList(list: List<String>, regex: Regex) = sequence {
  for (row in list.withIndex()) {
    for (match in regex.findAll(row.value)) {
      for (col in match.range) {
        yield(Triple(row.index, col, match))
      }
    }
  }
}

//
// Part 1
//
fun part1(input: List<String>): Int {
  val adjacentToParts = makeTable(input.size, input[0].length) { false }

  for ((row, col) in findAllInList(input, Regex("[^0-9.]+"))) {
    for ((i, j) in findAdjacentCells(adjacentToParts, row, col)) {
      adjacentToParts[i][j] = true
    }
  }

  val numbers = mutableSetOf<MatchResult>()
  for ((row, col, match) in findAllInList(input, Regex("[0-9]+"))) {
    if (adjacentToParts[row][col]) {
      numbers.add(match)
    }
  }

  return numbers.sumOf { it.value.toInt() }
}

//
// Part 2
//
fun part2(input: List<String>): Int {
  val adjacentToNumbers = makeTable(input.size, input[0].length) { mutableSetOf<MatchResult>() }

  for ((row, col, match) in findAllInList(input, Regex("[0-9]+"))) {
    for ((i, j) in findAdjacentCells(adjacentToNumbers, row, col)) {
      adjacentToNumbers[i][j] += match
    }
  }

  return findAllInList(input, Regex("[*]")).sumOf {
    with(adjacentToNumbers[it.first][it.second]) {
      if (size == 2) fold(1) { acc, next -> acc * next.value.toInt() } else 0.toInt()
    }
  }
}
