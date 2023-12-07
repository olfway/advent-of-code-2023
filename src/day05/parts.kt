package day05

//
// Common code
//
class Converter {
  private val map = mutableMapOf<LongRange, LongRange>()

  fun addRange(dst: Long, src: Long, length: Long) {
    map[src ..< (src + length)] = dst ..< (dst + length)
  }

  operator fun invoke(src: Long): Long {
    for ((key, value) in map.entries) {
      if (src in key) return value.first + (src - key.first)
    }
    return src
  }

  operator fun invoke(src: LongRange): List<LongRange> {
    var first = src.first
    val last = src.last
    val result = mutableListOf<LongRange>()
    for (range in map.keys.sortedBy { it.first }) {
      if (first < range.first) {
        if (last < range.first) {
          result.add(first..last)
          return result
        }
        result.add(first ..< range.first)
        first = range.first
      }
      if (first <= range.last) {
        if (last <= range.last) {
          result.add(this(first)..this(last))
          return result
        }
        result.add(this(first)..this(range.last))
        first = range.last + 1
      }
    }
    if (first < last) {
      result.add(first..last)
    }
    return result
  }
}

fun inputToConverters(input: List<String>): List<Converter> {
  val converters = mutableListOf<Converter>()
  for (line in input) {
    if (line.isEmpty()) continue
    if (line.endsWith("map:")) {
      converters.add(Converter())
      continue
    }
    with(line.split(" ").mapNotNull(String::toLongOrNull)) {
      converters.last().addRange(get(0), get(1), get(2))
    }
  }
  return converters
}

//
// Part 1
//
fun part1(input: List<String>): Long {
  val converters = inputToConverters(input.drop(1))
  val seeds = input.first().split(" ").mapNotNull(String::toLongOrNull)
  return seeds.minOf { seed -> converters.fold(seed) { value, converter -> converter(value) } }
}

//
// Part 2
//
fun part2(input: List<String>): Long {
  val converters = inputToConverters(input.drop(1))
  val seeds =
    input.first().split(" ").mapNotNull(String::toLongOrNull).chunked(2).map { it ->
      it.first() ..< (it.first() + it.last())
    }
  var ranges = seeds
  for (converter in converters) {
    ranges = ranges.map { converter(it) }.flatten()
  }

  return ranges.minOf { it.first() }
}
